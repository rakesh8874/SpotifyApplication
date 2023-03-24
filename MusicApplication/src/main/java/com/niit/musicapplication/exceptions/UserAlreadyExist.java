package com.niit.musicapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Might Be User already Present On Given User Name Please Provide")
public class UserAlreadyExist extends Exception{
}
