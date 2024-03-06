package com.platform.blogmanagement.blog.post.model;

import com.platform.blogmanagement.blog.user.model.UserModel;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Document(collection = "Posts")
@Builder @ToString
public class PostModel {
        @Id @BsonId
        private String postId;
        private String postName;
        @DocumentReference(collection = "Users")
        private UserModel postAuthor;
        private LocalDateTime postCreateTimestamp;
        private LocalDateTime postModifyTimestamp;
        private List<String> postTags;
        private List<String> postCategory;
        private String postBody;
        private String postTitle;
}
