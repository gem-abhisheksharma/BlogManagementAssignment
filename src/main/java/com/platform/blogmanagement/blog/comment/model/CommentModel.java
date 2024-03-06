package com.platform.blogmanagement.blog.comment.model;

import com.platform.blogmanagement.blog.user.model.UserModel;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Document(collection = "Comments")
public class CommentModel {
        @Id @BsonId
        private String commentId;
        private String postId;
        private String commentMsg;
        private LocalDateTime createTimestamp;
        private LocalDateTime modifyTimestamp;
        @DocumentReference(collection = "Users")
        private UserModel commentAuthor;

}
