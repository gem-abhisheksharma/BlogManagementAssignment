package com.platform.blogmanagement.blog.user.exceptionhandler;

import com.platform.blogmanagement.blog.user.dto.ConstraintViolation;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice("com.platform.blogmanagement.blog.user")
public class GlobalUserExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAllException(Exception exception){
        log.error("In handleAllException", exception);
        return ErrorResponse
                .builder(exception,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        exception.getMessage()
                )
                .build();
    }
}
