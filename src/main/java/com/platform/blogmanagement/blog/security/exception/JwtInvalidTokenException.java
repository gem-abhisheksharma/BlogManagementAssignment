package com.platform.blogmanagement.blog.security.exception;

public class JwtInvalidTokenException extends RuntimeException{
    public JwtInvalidTokenException() {
        super();
    }

    public JwtInvalidTokenException(String message) {
        super(message);
    }
}
