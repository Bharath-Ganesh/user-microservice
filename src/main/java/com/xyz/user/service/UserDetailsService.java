package com.xyz.user.service;

import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.response.UserResponse;

public interface UserDetailsService {

	public UserResponse getUserDetails(String userId) throws UserServiceException;

	public UserResponse getDetailsBasedOnUsernameOrEmailId(String username, String email) throws UserServiceException;

}
