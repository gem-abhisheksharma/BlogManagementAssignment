package com.platform.blogmanagement.blog.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
public class ConstraintViolation {
    private String fieldName;
    private String message;
    private String rejectedValue;
}
