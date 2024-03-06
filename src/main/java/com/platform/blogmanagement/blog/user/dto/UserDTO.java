package com.platform.blogmanagement.blog.user.dto;

import com.platform.blogmanagement.blog.user.model.UserRoles;
import jakarta.validation.constraints.*;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString @Builder
public class UserDTO {
    @NotNull @NotBlank @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{6,12}$")
    private String userId;
    @NotNull
    private String userFullName;
    @NotNull @NotBlank
    private String userPassword;
    @Size(min = 1,max = 2) @NotEmpty
    private Set<String> userRoles;
}
