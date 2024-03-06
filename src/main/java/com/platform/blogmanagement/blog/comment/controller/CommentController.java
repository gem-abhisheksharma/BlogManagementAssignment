package com.platform.blogmanagement.blog.comment.controller;

import com.platform.blogmanagement.blog.comment.dto.CommentDTO;
import com.platform.blogmanagement.blog.comment.service.CommentService;
import com.platform.blogmanagement.blog.user.model.UserRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/comment")
@Slf4j
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/{postId}")
    public ResponseEntity<?> getComment(@PathVariable String postId){
        log.info("GET:Post:Request /" + postId);
        List<CommentDTO> commentList = commentService.getCommentDetailsByPostId(postId);
        if(commentList != null){
            log.info("GET:Post:Response /" + postId,commentList);
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDetails){
        CommentDTO addedCommentDetails = commentService.createComment(commentDetails);
        if(addedCommentDetails != null){
            return new ResponseEntity<>(addedCommentDetails,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RolesAllowed(UserRoles.ROLE_USER)
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@Valid @RequestBody CommentDTO commentDetails){
        Optional<CommentDTO> updatedCommentDetails = commentService.updateComment(commentDetails);
        if(updatedCommentDetails.isPresent()){
            return new ResponseEntity<>(updatedCommentDetails.get(),HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RolesAllowed(UserRoles.ROLE_ADMIN)
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable String commentId){
        commentService.deleteComment(commentId);
    }

}
