package com.platform.blogmanagement.blog.comment.service;

import com.platform.blogmanagement.blog.comment.dto.CommentDTO;
import com.platform.blogmanagement.blog.comment.model.CommentModel;
import com.platform.blogmanagement.blog.comment.repository.CommentRepository;
import com.platform.blogmanagement.blog.user.model.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    final private CommentRepository commentRepository;
    final private ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<CommentDTO> getCommentDetails(String commentId){
        Optional<CommentModel> commentDetails = commentRepository.findById(commentId);
        return commentDetails.map(commentModel -> modelMapper.map(commentModel, CommentDTO.class));
    }

    public List<CommentDTO> getCommentDetailsByPostId(String postId){
        Optional<List<CommentModel>> commentList = commentRepository.findAllByPostId(postId);
        return commentList.map(commentModels -> commentModels.stream()
                .map(commentModel -> modelMapper.map(commentModel, CommentDTO.class))
                .collect(Collectors.toList())).orElse(null);
    }
    public CommentDTO createComment(CommentDTO commentDetails){
        return modelMapper.map(commentRepository.save(modelMapper.map(commentDetails,CommentModel.class)),CommentDTO.class);
    }

    public void deleteComment(String commentId){
        commentRepository.deleteById(commentId);
    }

    public Optional<CommentDTO> updateComment(CommentDTO commentDetails){
        if(getCommentDetails(commentDetails.getCommentId()).isPresent()){
            return Optional.of(modelMapper.map(commentRepository.save(modelMapper.map(commentDetails,CommentModel.class)),CommentDTO.class));
        }
        return Optional.empty();
    }
}
