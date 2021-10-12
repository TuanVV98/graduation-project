package com.spring.controller.v1.comment;

import com.spring.dto.model.CommentsDTO;
import com.spring.dto.response.Response;
import com.spring.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //read all comment của admin hoặc lễ tân
    @PreAuthorize("hasAnyRole('ADMIN' or 'RECEPTIONIST')")
    @GetMapping("/all")
    public ResponseEntity<Response<List<CommentsDTO>>> getAll(){
        Response<List<CommentsDTO>> response= new Response<>();
        response.setData(this.commentService.readAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // phải đăng nhập
    @PostMapping()
    public ResponseEntity<Response<CommentsDTO>> create(
            @RequestBody @Valid CommentsDTO commentsDTO,
            BindingResult bindingResult
    ){
        Response<CommentsDTO> response= new Response<>();
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().
                    forEach(error->response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.commentService.create(commentsDTO));
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    // chưa dùng đến
    @PutMapping("/{id}")
    public ResponseEntity<Response<CommentsDTO>> update(
            @RequestBody @Valid CommentsDTO commentsDTO,
            BindingResult bindingResult
    ){

        Response<CommentsDTO> response= new Response<>();
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().
                    forEach(error->response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.commentService.update(commentsDTO));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    // phải đăng nhập
    // xóa cứng
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<CommentsDTO>> delete(
            @PathVariable Long id
    ){
        Response<CommentsDTO> response=new Response<>();
        response.setData(this.commentService.delete(id));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //soft-delete mềm phải đăng nhập
    @PutMapping("/soft-delete/{id}")
    public ResponseEntity<Response<CommentsDTO>> soft_delete(
            @PathVariable Long id,
            @RequestParam Boolean deleteAt
    ){
        Response<CommentsDTO> response=new Response<>();
        response.setData(this.commentService.updateDeleteAt(id, deleteAt));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
 // phải đăng nhập
    // read all with deleteAt=TRUE
    @PreAuthorize("hasAnyRole('ADMIN' or 'RECEPTIONIST')")
    @GetMapping("/recycle-bin")
    public ResponseEntity<Response<List<CommentsDTO>>> getAllDeleteAtTrue(){
        Response<List<CommentsDTO>> response=new Response<>();
        response.setData(this.commentService.readAllDeleteAtTrue());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    // read all with deleteAt=FALSE  ai cũng xem được (hiển thị  chính )
    @GetMapping()
    public ResponseEntity<Response<List< CommentsDTO>>> getAllDeleteAtFalse(){
        Response<List<CommentsDTO>> response=new Response<>();
        response.setData(this.commentService.readAllDeleteAtFalse());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //read all comment by post_id của admin hoặc lễ tân
    @PreAuthorize("hasAnyRole('ADMIN' or 'RECEPTIONIST')")
    @GetMapping("/post")
    public ResponseEntity<Response<List<CommentsDTO>> > getAllCommentByPostId
    (
            @RequestParam Long postsId
    ){
        Response<List<CommentsDTO>> response=new Response<>();
        response.setData(this.commentService.readAllCommentByPostId(postsId));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //read all comment by post_id and account_id 
    @GetMapping("/post-account")
    public ResponseEntity<Response<List<CommentsDTO>> > getAllCommentByPostAndByAccount
    (
            @RequestParam Long postsId,
            @RequestParam Long accountsId
    ){
        Response<List<CommentsDTO>> response=new Response<>();
        response.setData(this.commentService.readAllCommentByPostIdAndAccountId(postsId,accountsId));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}