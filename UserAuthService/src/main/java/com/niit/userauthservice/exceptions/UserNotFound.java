package com.niit.userauthservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Might Be User is Not Available On Given Username")
public class UserNotFound extends Exception{
}
