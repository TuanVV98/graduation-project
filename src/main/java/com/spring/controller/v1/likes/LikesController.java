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

    //lay tat ca like cua post
    @GetMapping("/all/{id}")
    public List<LikesDTO> getAll(@PathVariable("id") Long id){
        return likesService.getLikeByPost(id);
    }

    //tim like theo acc vs post(dùng để hiển thị nút like của acc "like hay chưa like")
    @GetMapping("/acc/{ida}/post/{idp}")
    public LikesDTO getLikes(@PathVariable("ida") Long ida, @PathVariable("idp") Long idp){
        return likesService.getLikeByAccAndPost(ida, idp);
    }

    //create
    @PostMapping()
    public LikesDTO create(@RequestBody LikesDTO likesDTO){
        return likesService.create(likesDTO);
    }

    //delete
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        likesService.delete(id);
    }
}
