package com.niit.userauthservice.controller;
import com.niit.userauthservice.domain.User;
import com.niit.userauthservice.exceptions.UserAlreadyExist;
import com.niit.userauthservice.exceptions.UserNotFound;
import com.niit.userauthservice.security.GenerateJWT;
import com.niit.userauthservice.service.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authUser/")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    GenerateJWT generateJWT;

    @PostMapping("/register/")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExist {
     return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login/")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFound {
      User tempUser = userService.loginUser(user.getUsername(), user.getPassword());
      if(tempUser != null){
          Map<String, String> loginDetails =  generateJWT.generateJwtTocken(tempUser);
          return new ResponseEntity<>(loginDetails, HttpStatus.CREATED);
      }else{
          return new ResponseEntity<>("Invalid Or Missing Login Details", HttpStatus.NOT_FOUND);
      }
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.FOUND);
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request){
        Claims claims =  (Claims) request.getAttribute("username");
        String username = claims.getSubject();
        return  new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

}
