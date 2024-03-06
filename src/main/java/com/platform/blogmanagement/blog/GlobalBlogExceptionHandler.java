package com.platform.blogmanagement.blog;

import com.platform.blogmanagement.blog.user.dto.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice("com.platform.blogmanagement.blog")
public class GlobalBlogExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ProblemDetail validationProblemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Validation Error"
        );
        List<ConstraintViolation> errors = exception.getFieldErrors()
                .stream()
                .map(violation -> ConstraintViolation.builder()
                        .message(violation.getDefaultMessage())
                        .fieldName(violation.getField())
                        .rejectedValue(Objects.isNull(violation.getRejectedValue()) ?
                                "null" : violation.getRejectedValue().toString())
                        .build())
                .toList();
        validationProblemDetail.setProperty("errors",errors);
        log.error("In handleMethodArgumentNotValidException",exception);
        return validationProblemDetail;
    }
}
