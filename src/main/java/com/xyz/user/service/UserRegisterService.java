package com.xyz.user.service;

import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.request.UserRegistrationRequest;
import com.xyz.user.model.response.UserResponse;

public interface UserRegisterService {

	public UserResponse registerUser(UserRegistrationRequest userRegReq) throws UserServiceException;

}
