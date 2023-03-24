package com.niit.userauthservice.security;

import com.niit.userauthservice.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GenerateJWTImpl implements GenerateJWT{
    @Override
    public Map<String, String> generateJwtTocken(User user) {
        String jsonWebTocken = null;
        jsonWebTocken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date()).
                signWith(SignatureAlgorithm.HS256,"userKey").compact();

        Map<String ,String> loginStatus = new HashMap<>();
        loginStatus.put("token",jsonWebTocken);
        loginStatus.put("message","User Logged In Successfully");
        return loginStatus;
    }
}
