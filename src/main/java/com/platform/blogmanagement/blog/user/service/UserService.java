package com.platform.blogmanagement.blog.user.service;

import com.platform.blogmanagement.blog.security.JwtUtils;
import com.platform.blogmanagement.blog.user.dto.UserDTO;
import com.platform.blogmanagement.blog.user.dto.UserLoginRequest;
import com.platform.blogmanagement.blog.user.dto.AuthTokenResponse;
import com.platform.blogmanagement.blog.user.model.UserModel;
import com.platform.blogmanagement.blog.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    final private JwtUtils jwtUtils;
    final private UserRepository userRepository;
    final private ModelMapper modelMapper;
    public UserService(UserRepository userRepository, ModelMapper modelMapper, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtUtils = jwtUtils;
    }


    public Optional<UserDTO> getUserDetails(String userId){
        Optional<UserModel> userDetails = userRepository.findByUserId(userId);
        return userDetails.map(userModel -> modelMapper.map(userModel, UserDTO.class));
    }

    public UserDTO createUser(UserDTO userDetails){
        return modelMapper.map(userRepository.save(modelMapper.map(userDetails,UserModel.class)), UserDTO.class);
    }

    public AuthTokenResponse generateToken(UserLoginRequest userDetails){
        Optional<UserModel> userModel = userRepository.findByUserIdAndUserPassword(userDetails.getUserId(),userDetails.getUserPassword());
        return userModel.map(jwtUtils::generateTokenFromUserDetails).orElse(null);
    }
    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public Optional<UserDTO> updateUser(UserModel userDetails){
        if(getUserDetails(userDetails.getUserId()).isPresent()){
            return Optional.of(modelMapper.map(userRepository.save(userDetails), UserDTO.class));
        }
        return Optional.empty();
    }

    public List<UserDTO> getAllUserDetails() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getMyUser() {
        UserModel userDetails = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return modelMapper.map(userDetails,UserDTO.class);
    }
}
