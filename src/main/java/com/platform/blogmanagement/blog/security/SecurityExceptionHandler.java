package com.platform.blogmanagement.blog.security;

import com.platform.blogmanagement.blog.security.exception.JwtInvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.platform.blogmanagement.blog.security")
public class SecurityExceptionHandler {
    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail handleExpiredJwtException(ExpiredJwtException exception){
        log.error("In handleExpiredJwtException",exception);
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED,"Token expired");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ProblemDetail handleUsernameNotFoundException(UsernameNotFoundException exception){
        log.error("In UsernameNotFoundException",exception);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,exception.getMessage());
    }

    @ExceptionHandler(JwtInvalidTokenException.class)
    public ProblemDetail handleJwtInvalidTokenException(JwtInvalidTokenException exception){
        log.error("In handleJwtInvalidTokenException",exception);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
