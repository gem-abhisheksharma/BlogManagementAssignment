package com.platform.blogmanagement.blog.comment.dto;

import com.platform.blogmanagement.blog.user.dto.UserResponse;
import com.platform.blogmanagement.blog.user.model.UserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @Builder
public class CommentDTO {
    @NotNull @NotBlank
    private String postId;
    private String commentId;
    @NotNull @NotBlank
    private String commentMsg;
    @NotNull @PastOrPresent
    private LocalDateTime createTimestamp;
    @PastOrPresent
    private LocalDateTime modifyTimestamp;
    private UserResponse commentAuthor;
}
