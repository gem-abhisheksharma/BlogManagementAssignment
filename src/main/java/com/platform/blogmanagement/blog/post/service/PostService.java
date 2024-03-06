package com.platform.blogmanagement.blog.post.service;

import com.platform.blogmanagement.blog.post.dto.PostDTO;
import com.platform.blogmanagement.blog.post.model.PostModel;
import com.platform.blogmanagement.blog.post.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    final private PostRepository postRepository;
    final private ModelMapper modelMapper;

    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<PostDTO> getPostDetails(String postId){
        Optional<PostModel> postDetails = postRepository.findById(postId);
        return postDetails.map(postModel -> modelMapper.map(postModel, PostDTO.class));
    }

    public PostDTO createPost(PostDTO postDetails){
        return modelMapper.map(postRepository.save(modelMapper.map(postDetails,PostModel.class)),PostDTO.class);
    }

    public void deletePost(String postId){
        postRepository.deleteById(postId);
    }

    public Optional<PostDTO> updatePost(PostModel postDetails){
        if(getPostDetails(postDetails.getPostId()).isPresent()){
            return Optional.of(modelMapper.map(postRepository.save(postDetails),PostDTO.class));
        }
        return Optional.empty();
    }

    public List<PostDTO> searchByNameAndBody(HashMap<String, String> searchInput) {
        Optional<List<PostModel>> postList = Optional.empty();
        if(searchInput.containsKey("postName")){
            String postName = searchInput.get("postName");
            if(!postName.isBlank()){
                postList = postRepository.findByPostNameLikeIgnoreCase(postName);
            }

        } else if (searchInput.containsKey("postBody")) {
            String postBody = searchInput.get("postBody");
            if(!postBody.isBlank()){
                postList = postRepository.findByPostBodyLikeIgnoreCase(postBody);
            }
        }
        return postList.map(postModels -> postModels
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList())).orElse(null);
    }

    public List<PostDTO> filterByCategory(String category) {
        Optional<List<PostModel>> postList = Optional.empty();
        if(!category.isEmpty()){
            postList = postRepository.findByPostCategory(category);
        }
        return postList.map(postModels -> postModels
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList())).orElse(null);
    }
    public List<PostDTO> filterByTag(String tag) {
        Optional<List<PostModel>> postList = Optional.empty();
        if(!tag.isEmpty()){
            postList = postRepository.findByPostTags(tag);
        }
        return postList.map(postModels -> postModels
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList())).orElse(null);
    }

    public List<PostDTO> getAllPostDetails() {
        List<PostModel> postList = postRepository.findAll();
        return postList.stream()
                .map(postModel -> modelMapper.map(postModel, PostDTO.class))
                .collect(Collectors.toList());
    }
}
