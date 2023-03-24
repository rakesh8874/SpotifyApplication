package com.niit.musicapplication.proxy;

import com.niit.musicapplication.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-auth-service", url="localhost:8084")
public interface IUserProxy {

    @PostMapping("/authUser/register/")
    ResponseEntity<?> registerUser(@RequestBody User user);

}
