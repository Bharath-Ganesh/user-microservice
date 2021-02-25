package com.xyz.user.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.user.constants.Roles;
import com.xyz.user.entity.User;
import com.xyz.user.model.response.UserResponse;
import com.xyz.user.repository.UserRepository;
import com.xyz.user.service.UserFilterService;

@Service
public class UserFilterServiceImpl implements UserFilterService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsUpdateServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	// This method has to be created an util class. Can be used by all the services
	private UserResponse convertEntityIntoResponse(User user) {
		logger.info("Inside convertEntityIntoResponse method ");
		UserResponse userResponse = new UserResponse();
		if (null != user) {
			userResponse.setUserId(user.getUserId());
			userResponse.setAddress(user.getAddress());
			userResponse.setAvailability(user.getAvailablity());
			userResponse.setEmailId(user.getEmailId());
			userResponse.setName(user.getName());
			userResponse.setRole(user.getRole());
		}
		logger.info("Exiting convertEntityIntoResponse method ");
		return userResponse;

	}

	@Override
	public List<UserResponse> filterUsers(String location, String availablity) {
		logger.info("Inside filterUsers method ");
		List<UserResponse> userList = new ArrayList<>();
		Optional<List<User>> optionalUsersList = null;
		if (!availablity.isEmpty() || !location.isEmpty()) {

			String role = Roles.DELIVERY_AGENT.getRole();
			if (!availablity.isEmpty() && !location.isEmpty()) {
				optionalUsersList = userRepository.findByAddressAndAvailablityAndRole(location, availablity, role);
			} else if (!availablity.isEmpty()) {
				optionalUsersList = userRepository.findByAvailablityAndRole(availablity, role);

			} else if (!location.isEmpty()) {
				optionalUsersList = userRepository.findByAddressAndRole(location, role);
			}

		} else {
			// Custom exception
		}
		if (optionalUsersList.isPresent()) {
			for (User user : optionalUsersList.get()) {
				UserResponse userResponse = convertEntityIntoResponse(user);
				userList.add(userResponse);
			}

		}
		logger.info("Exiting filterUsers method ");
		return userList;
	}

}
