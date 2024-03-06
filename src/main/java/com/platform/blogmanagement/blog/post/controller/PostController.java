package com.platform.blogmanagement.blog.post.controller;

import com.platform.blogmanagement.blog.post.dto.PostDTO;
import com.platform.blogmanagement.blog.post.service.PostService;
import com.platform.blogmanagement.blog.user.model.UserRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable String postId){
        Optional<PostDTO> postDetails = postService.getPostDetails(postId);
        return postDetails.map(postDTO -> new ResponseEntity<>(postDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping
    public ResponseEntity<?> getAllPost(){
        List<PostDTO> postList = postService.getAllPostDetails();
        if(postList != null){
            return new ResponseEntity<>(postList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDetails){
        PostDTO addedPostDetails = postService.createPost(postDetails);
        if(addedPostDetails != null){
            return new ResponseEntity<>(addedPostDetails, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePost(@Valid @RequestBody PostDTO postDetails){
        PostDTO addedPostDetails = postService.createPost(postDetails);
        if(addedPostDetails != null){
            return new ResponseEntity<>(addedPostDetails,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RolesAllowed(UserRoles.ROLE_ADMIN)
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable String postId){
        postService.deletePost(postId);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchByNameAndComment(@RequestBody Map<String,String> searchInput){
        if(searchInput != null){
            List<PostDTO> postList = postService.searchByNameAndBody((HashMap<String, String>) searchInput);
            if(postList != null){
                return new ResponseEntity<>(postList,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/filter")
    public  ResponseEntity<?> filterTagOrCategory(@RequestParam(name = "tag", required = false) String tag,
                                                  @RequestParam(name = "category", required = false) String category){
        List<PostDTO> postList = null;
        if(tag != null) {
            postList = postService.filterByTag(tag);
        } else if (category != null) {
            postList = postService.filterByCategory(category);
        }
        if(postList != null){
                return new ResponseEntity<>(postList,HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
