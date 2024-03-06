package com.platform.blogmanagement.blog.post.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "Tags")
@Builder
@ToString
public class Tag {
    @Id
    private String name;
}