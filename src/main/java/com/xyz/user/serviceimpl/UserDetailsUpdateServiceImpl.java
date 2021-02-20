package com.xyz.user.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.user.entity.User;
import com.xyz.user.model.response.UserResponse;
import com.xyz.user.repository.UserRepository;
import com.xyz.user.service.UserDetailsUpdateService;

@Service
public class UserDetailsUpdateServiceImpl implements UserDetailsUpdateService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsUpdateServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponse updateUserAvailabilityStatus(String userId, String status) {
		// TODO Auto-generated method stub
		logger.info("Inside updateUserAvailabilityStatus method ");
		UserResponse userResponse = new UserResponse();
		String message = "";
		if (!userId.isEmpty() && !status.isEmpty()) {

			Integer id = Integer.parseInt(userId);
			Optional<User> userExist = userRepository.findById(id);
			if (!userExist.isEmpty()) {
				try {
					User user = userExist.get();
					if (status.equals("A") || status.equals("N")) {
						user.setAvailablity(status);
						userRepository.save(user);
						message = "Status updated successfully";
					}

				} catch (Exception ex) {
					logger.info("Caught Exception while saving into database", ex);
					message = "Something Went Wrong";
				}

			} else {
				/*
				 * Custom exception
				 */
				// we can use user microservice exception class to throw exception
				message = "Something Went Wrong";
			}
		}
		logger.info("Exitinh updateUserAvailabilityStatus method ");
		userResponse.setMessage(message);
		return userResponse;
	}

}
