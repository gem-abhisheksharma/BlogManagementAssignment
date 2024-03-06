package com.platform.blogmanagement.blog.post.exception;

public class PostNotExistException extends RuntimeException{

    public PostNotExistException(){
    super();
    }
    public PostNotExistException(String message) {
        super(message);
    }
}
