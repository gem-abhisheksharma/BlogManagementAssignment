package com.platform.blogmanagement.blog.user.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class UserResponse{
    private String userId;
    private String userFullName;
    private Set<String> userRoles;
}
