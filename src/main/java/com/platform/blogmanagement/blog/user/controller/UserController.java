package com.platform.blogmanagement.blog.user.controller;

import com.platform.blogmanagement.blog.user.dto.UserDTO;
import com.platform.blogmanagement.blog.user.dto.UserLoginRequest;
import com.platform.blogmanagement.blog.user.dto.AuthTokenResponse;
import com.platform.blogmanagement.blog.user.model.UserRoles;
import com.platform.blogmanagement.blog.user.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RolesAllowed(UserRoles.ROLE_ADMIN)
    @GetMapping()
    public ResponseEntity<?> getUser(){
        List<UserDTO> userList = userService.getAllUserDetails();
        return new ResponseEntity<>(userList, HttpStatus.OK);

    }

    @RolesAllowed(UserRoles.ROLE_USER)
    @GetMapping("/me")
    public ResponseEntity<?> getMyUser(){
        UserDTO userDetails =userService.getMyUser();
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDetails){
        UserDTO addedPostDetails = userService.createUser(userDetails);
        if(addedPostDetails != null){
            return new ResponseEntity<>(addedPostDetails, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authToken")
    public ResponseEntity<?> getAuthToken(@Valid @RequestBody UserLoginRequest userDetails){
        AuthTokenResponse authTokenResponse = userService.generateToken(userDetails);
        return  new ResponseEntity<>(authTokenResponse,HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDetails){
        UserDTO addedPostDetails = userService.createUser(userDetails);
        if(addedPostDetails != null){
            return new ResponseEntity<>(addedPostDetails,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }

}
