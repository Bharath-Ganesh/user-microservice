package com.xyz.user.service;

import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.request.UserLoginRequest;
import com.xyz.user.model.response.UserResponse;

public interface UserLoginInService {

	UserResponse loginIn(UserLoginRequest userLoginRequest) throws UserServiceException;

}
