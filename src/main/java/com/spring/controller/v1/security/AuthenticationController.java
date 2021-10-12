package com.spring.controller.v1.security;

import com.spring.dto.model.AccountsDTO;
import com.spring.dto.model.security.JwtAccountDTO;
import com.spring.dto.model.security.TokenDTO;
import com.spring.dto.response.Response;
import com.spring.model.Accounts;
import com.spring.model.security.JwtUserDetailsImpl;
import com.spring.service.account.AccountService;
import com.spring.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/user/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    private AccountService accountService;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            UserDetailsService userDetailsService,
            AccountService accountService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Response<TokenDTO>> generateTokenJwt(@Valid @RequestBody JwtAccountDTO dto, BindingResult result){

        Response<TokenDTO> response = new Response<>();

//        System.out.println("email : "+dto.getEmail());
//        System.out.println("pass : "+dto.getPassword());

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        if(this.accountService.checkIfEmailExistsAndDeletedAt(dto.getEmail()).isEmpty()){
            response.addErrorMsgToResponse("Email or password Incorrect !!");
            return ResponseEntity.badRequest().body(response);
        }

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtUserDetailsImpl userDetails = (JwtUserDetailsImpl) authentication.getPrincipal();

        String token = jwtTokenUtil.getToken(userDetails);
        response.setData(new TokenDTO(token));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<AccountsDTO>> getAccount(Authentication principal){

        Response<AccountsDTO> response = new Response<>();

        JwtUserDetailsImpl userDetails = (JwtUserDetailsImpl) principal.getPrincipal();
//        System.out.println("principal :"+ userDetails.getEmail());
        Optional<Accounts> account = this.accountService.checkIfEmailExistsAndDeletedAt(userDetails.getEmail());

        account.ifPresent(accounts -> response.setData(new AccountsDTO(accounts.getId(), accounts.getRoles().getName())));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
