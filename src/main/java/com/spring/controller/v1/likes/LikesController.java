package com.spring.controller.v1.likes;

import com.spring.dto.model.LikesDTO;
import com.spring.service.likes.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/likes")
public class LikesController {
	@Autowired
	private LikesService likesService;

	// lay tat ca like cua post ai cũng xem được
	@GetMapping("/all/{id}")
	public List<LikesDTO> getAll(@PathVariable("id") Long id) {
		return likesService.getLikeByPost(id);
	}

	// phải đăng nhập
	// tim like theo acc vs post(dùng để hiển thị nút like của acc "like hay chưa
	// like")
	// xủ lý fontend nếu tìm thấy - delete, nếu ko tìm thấy - create
	@GetMapping("/acc/{ida}/post/{idp}")
	public LikesDTO getLikes(@PathVariable("ida") Long ida, @PathVariable("idp") Long idp) {
		return likesService.getLikeByAccAndPost(ida, idp);
	}

	// phải đăng nhập
	// create
	@PostMapping()
	public LikesDTO create(@RequestBody LikesDTO likesDTO) {
		return likesService.create(likesDTO);
	}

	// phải đăng nhập
	// delete
	@DeleteMapping("{id}")
	public LikesDTO delete(@PathVariable("id") Long id) {
		return likesService.delete(id);
	}
}