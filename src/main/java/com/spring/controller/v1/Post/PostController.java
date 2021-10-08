package com.spring.controller.v1.Post;

import com.spring.dto.model.PostDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<PostDTO>> create(
            @RequestPart("properties") @Valid PostDTO dto,
            @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
            BindingResult result
    ) {

        Response<PostDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.postService.save(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<PostDTO>> updateWithFile(
            @RequestPart("properties") @Valid PostDTO dto,
            @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
            BindingResult result
    ) {

        Response<PostDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.postService.update(dto));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Response<PostDTO>> update(
            @Valid @RequestBody PostDTO dto,
            BindingResult result
    ) {

        Response<PostDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.postService.update(dto));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/by-title/{title}")
    public ResponseEntity<Response<List<PostDTO>>> findByTitle(@PathVariable("title") String title) {

        Response<List<PostDTO>> response = new Response<>();

        List<PostDTO> entity = this.postService.findByTitle(title);

        response.setData(entity);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<Optional<PostDTO>>> findById(
            @PathVariable("id") Long postId
    ) throws NotFoundException {

        Response<Optional<PostDTO>> response = new Response<>();

        Optional<PostDTO> post = this.postService.findById(postId);

        if (post.isEmpty()) {
            throw new NotFoundException("Không tìm thấy bài viết với tài khoản có ID : " + postId);
        }

        response.setData(post);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping()
    public ResponseEntity<Response<List<PostDTO>>> findAll() {

        Response<List<PostDTO>> response = new Response<>();

        List<PostDTO> entity = this.postService.findAll();

        response.setData(entity);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
