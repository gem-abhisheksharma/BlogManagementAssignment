package com.platform.blogmanagement.blog.post.exception;

public class PostAlreadyExistException extends RuntimeException{

    public PostAlreadyExistException(){
        super();
    }
    public PostAlreadyExistException(String message) {
        super(message);
    }
}
