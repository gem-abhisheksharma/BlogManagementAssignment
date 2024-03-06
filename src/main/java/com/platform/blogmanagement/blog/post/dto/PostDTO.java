package com.platform.blogmanagement.blog.post.dto;

import com.platform.blogmanagement.blog.user.dto.UserResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString @Builder
public class PostDTO {
    private String postId;
    @NotNull @NotBlank
    private String postName;
    @NotNull
    private UserResponse postAuthor;
    @NotNull @PastOrPresent
    private LocalDateTime postCreateTimestamp;
    @PastOrPresent
    LocalDateTime postModifyTimestamp;
    List<String> postTags;
    List<String> postCategory;
    @NotNull @NotBlank
    private String postBody;
    @NotNull @NotBlank
    private String postTitle;
}
