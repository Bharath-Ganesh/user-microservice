package com.xyz.user.exception.handler;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.error.Error;

@ControllerAdvice
public class UserServiceExceptionHandler {

	@ExceptionHandler(value = UserServiceException.class)
	public ResponseEntity<Object> exception(UserServiceException userServiceException) {
		Error error = new Error();
		error.setErrorCode(userServiceException.getErrorCode());
		error.setErrorMessage(userServiceException.getErrorMessage());
		List<Error> errorList = new ArrayList<>();
		errorList.add(error);
		return new ResponseEntity<>(error, userServiceException.getHttpStatusCode());
	}

}
