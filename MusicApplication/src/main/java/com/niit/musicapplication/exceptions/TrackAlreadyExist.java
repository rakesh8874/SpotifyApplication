package com.niit.musicapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Track Already Exist")
public class TrackAlreadyExist extends Exception{
}
