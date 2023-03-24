package com.niit.userauthservice.service;

import com.niit.userauthservice.domain.User;
import com.niit.userauthservice.exceptions.UserAlreadyExist;
import com.niit.userauthservice.exceptions.UserNotFound;

import java.util.List;

public interface IUserService {
    User registerUser(User user) throws UserAlreadyExist;
    User loginUser(String username, String password) throws UserNotFound;
    List<User> getAllUser();
    User getUserByUsername(String username);



}
