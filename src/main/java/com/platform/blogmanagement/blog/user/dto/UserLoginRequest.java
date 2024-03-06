package com.platform.blogmanagement.blog.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@ToString @Builder
public class UserLoginRequest {
    @NotNull @NotBlank
    private String userId;
    @NotNull @NotBlank
    private String userPassword;
}