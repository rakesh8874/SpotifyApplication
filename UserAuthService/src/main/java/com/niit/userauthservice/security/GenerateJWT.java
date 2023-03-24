package com.niit.userauthservice.security;

import com.niit.userauthservice.domain.User;

import java.util.Map;

public interface GenerateJWT {
    Map<String, String> generateJwtTocken(User user);
}
