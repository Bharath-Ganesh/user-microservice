package com.xyz.user.serviceimpl;

import java.util.Base64;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xyz.user.constants.Roles;
import com.xyz.user.constants.UserServiceErrorCode;
import com.xyz.user.constants.UserServiceErrorMessage;
import com.xyz.user.entity.User;
import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.request.UserRegistrationRequest;
import com.xyz.user.model.response.UserResponse;
import com.xyz.user.repository.UserRepository;
import com.xyz.user.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	private static final Logger logger = LoggerFactory.getLogger(UserRegisterServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	// strict regex
	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{5,18}";

	private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

	/**
	 * 
	 * @param userRegReq
	 * @return
	 * @throws UserServiceException 
	 */
	public User convertIntoEntity(UserRegistrationRequest userRegReq) throws UserServiceException {
		User user = new User();

		if (!userRegReq.getEmailId().isEmpty() && !userRegReq.getName().isEmpty()
				&& !userRegReq.getUsername().isEmpty()) {
			user.setAddress(userRegReq.getAddress());
			user.setEmailId(userRegReq.getEmailId());

			Matcher matcher = pattern.matcher(userRegReq.getUsername());
			if (matcher.matches()) {
				user.setUsername(userRegReq.getUsername());
			}else {
				throw new UserServiceException(UserServiceErrorCode.INVALID_USERNAME_FORMAT.getCode(),
						UserServiceErrorMessage.INVALID_USERNAME_FORMAT.getMessage(), HttpStatus.BAD_REQUEST, null);
			}

			user.setName(userRegReq.getName());

			/*
			 * Password encoding
			 */
			String encodedPassword = Base64.getEncoder().encodeToString((userRegReq.getPassword()).getBytes());
			user.setPassword(encodedPassword);
		}

		String role = "";
		String availablity = "";

		/*
		 * Switch case
		 */
		if (userRegReq.getRole().equals(Roles.ADMIN.name())) {
			role = Roles.ADMIN.getRole();
			availablity = "N";

		} else {
			role = Roles.DELIVERY_AGENT.getRole();
			availablity = "A";
		}
		user.setRole(role);
		user.setAvailablity(availablity);
		return user;

	}

	@Override
	public UserResponse registerUser(UserRegistrationRequest userRegReq) throws UserServiceException {
		UserResponse userResponse = null;
		String message = "";
		if (null != userRegReq) {
			
			if(userRegReq.getRole().isEmpty()) {
				userRegReq.setRole("DELIVERY_AGENT");
			}
	
			if (null != userRegReq.getConfirmPassword() && null != userRegReq.getConfirmPassword()) {

				if (userRegReq.getConfirmPassword().equals(userRegReq.getConfirmPassword())) {
					String username = userRegReq.getUsername();
					userResponse = new UserResponse();

					if (!userRegReq.getRole().isEmpty()) {

						/*
						 * The assigning of role needs to be refactored to a separate function. Used in
						 * convertIntoEntity function
						 */
						String role = "";

						if (userRegReq.getRole().equals(Roles.ADMIN.name())) {
							role = Roles.ADMIN.getRole();

						} else {
							role = Roles.DELIVERY_AGENT.getRole();

						}

						Optional<User> userExist = userRepository.findByUsername(username);
						if (!userExist.isPresent()) {
							User user = convertIntoEntity(userRegReq);
							try {
								userRepository.save(user);
								message = "User created successfully";
							} catch (Exception ex) {
								logger.info("Caught Exception while saving into database", ex);
								message = "Something Went Wrong";
							}

						} else {
							message = "User already registered.";
						}
					} else {
						/*
						 * Custom service exception to handle the functionality error
						 */
						message = "Something Went Wrong";
					}

				}

			}
			userResponse.setMessage(message);

		}
		return userResponse;
	}

}
