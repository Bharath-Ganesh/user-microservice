package com.xyz.user.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xyz.user.constants.UserServiceErrorCode;
import com.xyz.user.constants.UserServiceErrorMessage;
import com.xyz.user.entity.User;
import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.response.UserResponse;
import com.xyz.user.repository.UserRepository;
import com.xyz.user.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsUpdateServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	// This method has to be created an util class. Can be used by all the services
	
	/**
	 * 
	 * @param user
	 * @return UserResponse
	 */
	private UserResponse convertEntityIntoResponse(User user) {
		logger.info("Inide convertEntityIntoResponse method : ");
		UserResponse userResponse = new UserResponse();
		if (null != user) {
			userResponse.setUserId(user.getUserId());
			userResponse.setAddress(user.getAddress());
			userResponse.setAvailability(user.getAvailablity());
			userResponse.setEmailId(user.getEmailId());
			userResponse.setName(user.getName());
			userResponse.setRole(user.getRole());
		}
		logger.info("Exiting convertEntityIntoResponse method : ");
		return userResponse;

	}

	/**
	 * @param userId
	 * @return UserResponse
	 */
	public UserResponse getUserDetails(String userId) throws UserServiceException {
		// TODO Auto-generated method stub

		logger.info("Inide getUserdetails method : Userid : {}", userId);
		UserResponse userResponse = null;
		if (!userId.isEmpty()) {

			Integer id = Integer.parseInt(userId);
			Optional<User> userExist = userRepository.findById(id);
			if (!userExist.isEmpty()) {

				userResponse = convertEntityIntoResponse(userExist.get());

			} else {
				// Exception is thrown when a user is not found
				throw new UserServiceException(UserServiceErrorCode.USER_NOT_FOUND.getCode(),
						UserServiceErrorMessage.USER_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST, null);
			}

		} else {
			// Exception is thrown when a user is not found
			throw new UserServiceException(UserServiceErrorCode.USER_NOT_FOUND.getCode(),
					UserServiceErrorMessage.USER_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST, null);
		}
		return userResponse;
	}

	@Override
	public UserResponse getDetailsBasedOnUsernameOrEmailId(String username, String emailId) throws UserServiceException {
		// TODO Auto-generated method stub

		logger.info("Inide getDetailsBasedOnUsernameOrEmailId method : username : " + "{} && emaildId : {}", username,
				emailId);
		UserResponse userResponse = null;

		if (username.isEmpty()) {
			if (!emailId.isEmpty()) {
				Optional<User> userExist = userRepository.findByEmailId(emailId);

				if (!userExist.isEmpty()) {

					userResponse = convertEntityIntoResponse(userExist.get());
				} else {
					// Exception is thrown when a user is not found
					throw new UserServiceException(UserServiceErrorCode.USER_NOT_FOUND.getCode(),
							UserServiceErrorMessage.USER_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST, null);
				}

			}
		} else {
			Optional<User> userExist = userRepository.findByUsername(username);
			if (!userExist.isEmpty()) {

				userResponse = convertEntityIntoResponse(userExist.get());
			} else {
				// Exception is thrown when a user is not found
				throw new UserServiceException(UserServiceErrorCode.USER_NOT_FOUND.getCode(),
						UserServiceErrorMessage.USER_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST, null);
			}
		}
		return userResponse;

	}

}
