package com.niit.userauthservice.service.Impl;

import com.niit.userauthservice.domain.User;
import com.niit.userauthservice.exceptions.UserAlreadyExist;
import com.niit.userauthservice.exceptions.UserNotFound;
import com.niit.userauthservice.repository.UserRepository;
import com.niit.userauthservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) throws UserAlreadyExist {
        User existUser = userRepository.findByUsername(user.getUsername());
        if(existUser != null){
            throw new UserAlreadyExist();
        }else {
            existUser = userRepository.save(user);
            return existUser;
        }
   }

    @Override
    public User loginUser(String username, String password) throws UserNotFound {
        User testUser = userRepository.findByUsername(username);
        if (testUser != null) {
            if (testUser.getUsername().equals(username) && testUser.getPassword().equals(password)) {
                return testUser;
            } else {
                throw new UserNotFound();
            }
        }
        throw new UserNotFound();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}

