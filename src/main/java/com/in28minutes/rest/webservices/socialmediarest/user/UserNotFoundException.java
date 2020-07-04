package com.in28minutes.rest.webservices.socialmediarest.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

//source->generate constructor from superclass->select RuntimeException(string)
//when create this class, set java.lang.RuntimeException as superclass
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	

}
