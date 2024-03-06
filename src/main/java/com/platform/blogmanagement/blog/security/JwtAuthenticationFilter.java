package com.platform.blogmanagement.blog.security;
import com.platform.blogmanagement.blog.security.exception.JwtInvalidTokenException;
import com.platform.blogmanagement.blog.user.model.UserModel;
import com.platform.blogmanagement.blog.user.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(UserRepository userRepository, JwtUtils jwtUtils){
        this.userRepository = userRepository;
        this. jwtUtils = jwtUtils;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String reqToken = request.getHeader("Authorization");
        if (reqToken != null && reqToken.startsWith("Bearer ")) {
            String finalToken = reqToken.substring(7);
            Optional<String> userId = jwtUtils.getUserIdFromJwtToken(finalToken);
            if(userId.isPresent()){
                Optional<UserModel> userDetails = userRepository.findByUserId(userId.get());
                if(userDetails.isPresent()){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.get(), finalToken, userDetails.get().getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                else{
                    throw new UsernameNotFoundException("User : " + userId.get() + " Not found");
                }
            }
            else{
                throw new JwtInvalidTokenException("Invalid Token");
            }
        }
        filterChain.doFilter(request, response);
    }


}