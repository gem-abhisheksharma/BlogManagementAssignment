package com.platform.blogmanagement.blog.user.repository;

import com.platform.blogmanagement.blog.user.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByUserId(String userId);
    Optional<UserModel> findByUserIdAndUserPassword(String userId, String userPassword);
}
