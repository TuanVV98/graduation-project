package com.spring.controller.v1;

import com.spring.dto.model.AccountsDTO;
import com.spring.dto.response.Response;
import com.spring.service.accounts.AccountsService;
import com.spring.service.accounts.AccountsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountsService accountsService;

    @GetMapping
    public ResponseEntity<List<AccountsDTO>> getAll(){
        return new ResponseEntity<>(accountsService.findAll(), HttpStatus.OK);
    }

}
