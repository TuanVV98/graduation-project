package com.spring.controller.v1.post;

import com.spring.dto.model.PostDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/posts")
public class PostController {

	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PreAuthorize("hasAnyRole('ADMIN' or 'RECEPTIONIST')")
	@PostMapping()
	public ResponseEntity<Response<PostDTO>> create(@Valid @RequestBody PostDTO dto, BindingResult result) {

		Response<PostDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.postService.save(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PreAuthorize("hasAnyRole('ADMIN' or 'RECEPTIONIST')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<PostDTO>> update(@Valid @RequestBody PostDTO dto, BindingResult result) {

		Response<PostDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.postService.update(dto));

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAnyRole('ADMIN' or 'RECEPTIONIST')")
	@DeleteMapping("/{id}")
	public void deletePost(@PathVariable("id") Long id) throws NotFoundException {
		this.postService.hardDelete(id);
	}

	@GetMapping(value = "/by-title")
	public ResponseEntity<Response<List<PostDTO>>> findByTitle(@RequestParam(required = false) String title) {

		Response<List<PostDTO>> response = new Response<>();

		List<PostDTO> entity = this.postService.findByTitle(title);

		response.setData(entity);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Optional<PostDTO>>> findById(@PathVariable("id") Long postId)
			throws NotFoundException {

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
