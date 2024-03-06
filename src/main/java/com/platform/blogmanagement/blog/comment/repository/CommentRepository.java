package com.platform.blogmanagement.blog.comment.repository;

import com.platform.blogmanagement.blog.comment.model.CommentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<CommentModel, String> {
    Optional<List<CommentModel>> findAllByPostId(String postId);

    @Update("{ '$set' : { 'commentMsg' : ?0 } }")
    int findAndSetCommentMsgByCommentId(String commentId, String commentMsg);
}
