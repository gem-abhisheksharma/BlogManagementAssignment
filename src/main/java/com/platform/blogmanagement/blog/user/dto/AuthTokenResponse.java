package com.platform.blogmanagement.blog.user.dto;

import org.springframework.beans.factory.annotation.Value;

public record AuthTokenResponse(String access_token,
                                String token_type,
                                Integer expire_in) {}
