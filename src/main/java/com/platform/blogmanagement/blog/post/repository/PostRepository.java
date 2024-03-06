package com.platform.blogmanagement.blog.post.repository;

import com.platform.blogmanagement.blog.post.model.PostModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<PostModel, String> {
    Optional<List<PostModel>> findByPostNameLikeIgnoreCase(String postName);

    Optional<List<PostModel>> findByPostBodyLikeIgnoreCase(String postBody);

    Optional<List<PostModel>> findByPostCategory(String postCategory);


    Optional<List<PostModel>> findByPostTags(String postTags);
}
