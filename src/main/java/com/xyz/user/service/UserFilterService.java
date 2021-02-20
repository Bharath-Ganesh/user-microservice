package com.xyz.user.service;

import java.util.List;

import com.xyz.user.model.response.UserResponse;

public interface UserFilterService {

	public List<UserResponse> filterUsers(String location, String availablity);

}
