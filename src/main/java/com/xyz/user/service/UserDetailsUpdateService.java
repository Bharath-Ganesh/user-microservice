package com.xyz.user.service;

import com.xyz.user.model.response.UserResponse;

public interface UserDetailsUpdateService {

	UserResponse updateUserAvailabilityStatus(String userId, String status);

}
