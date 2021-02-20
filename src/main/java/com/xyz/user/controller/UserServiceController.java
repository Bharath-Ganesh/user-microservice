package com.xyz.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.user.entity.User;
import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.request.UserLoginRequest;
import com.xyz.user.model.request.UserRegistrationRequest;
import com.xyz.user.model.response.UserResponse;
import com.xyz.user.service.UserDetailsService;
import com.xyz.user.service.UserDetailsUpdateService;
import com.xyz.user.service.UserFilterService;
import com.xyz.user.service.UserLoginInService;
import com.xyz.user.service.UserRegisterService;

import io.swagger.annotations.*;

@CrossOrigin
@Api(value = "User Controller", tags = { "User controller" })
@RestController
@RequestMapping("/user")
public class UserServiceController {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);

	@Autowired
	private UserLoginInService loginInService;

	@Autowired
	private UserDetailsUpdateService UserDetailsUpdateService;

	@Autowired
	private UserRegisterService userRegisterService;

	@Autowired
	private UserFilterService userFilterService;

	@Autowired
	private UserDetailsService userDetailsService;

	@ApiOperation(value = "Login as a user", notes = "This method allows a user login", response = UserResponse.class)
	@PostMapping("/login")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "User login successful", response = UserResponse.class) })
	public ResponseEntity<UserResponse> loginIn(@RequestBody UserLoginRequest userLoginRequest)
			throws UserServiceException {

		logger.info("Entered UserLoginController ; Username : {} ", userLoginRequest.getUsername());
		UserResponse userResponse = loginInService.loginIn(userLoginRequest);
		logger.info("Exited UserLoginController ; Username : {} ", userLoginRequest.getUsername());

		return new ResponseEntity<>(userResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Registration for an admin or agent", response = UserResponse.class)
	@PostMapping("/register")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "User registered successful", response = UserResponse.class) })
	public UserResponse registerUser(@RequestBody UserRegistrationRequest userRegReq) throws UserServiceException {

		logger.info("Entered UserRegisterController , {}", userRegReq.getUsername());

		UserResponse userResponse = userRegisterService.registerUser(userRegReq);

		logger.info("Exited UserRegisterController ; {}", userRegReq.getUsername());

		return userResponse;

	}

	@ApiOperation(value = "Fetches the details of an employee based on their user id", response = UserResponse.class)
	@GetMapping("/{id}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "User registered successful", response = UserResponse.class) })
	public ResponseEntity<UserResponse> getUserDetails(@PathVariable("id") String userId) throws UserServiceException {

		logger.info("Entered UserRegisterController , getUserDetails method : {}", System.currentTimeMillis());

		UserResponse userResponse = userDetailsService.getUserDetails(userId);

		logger.info("Exited UserRegisterController ; getUserDetails method : {}", System.currentTimeMillis());

		return new ResponseEntity<>(userResponse, HttpStatus.OK);

	}

	@ApiOperation(value = "Fetches the details of an employee either on the basis of emailId or username", response = UserResponse.class)
	@GetMapping("/userdetails")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "User registered successful", response = UserResponse.class) })
	public ResponseEntity<UserResponse> getUserDetailsBasedOnUsernameOrEemailId(
			@RequestParam(name = "username", required = false, defaultValue = "") String username,
			@RequestParam(name = "emailId", required = false, defaultValue = "") String emailId)
			throws UserServiceException {

		logger.info("Entered UserRegisterController , getUserDetailsBasedOnUsernameOrEemailId method : {}",
				System.currentTimeMillis());

		UserResponse userResponse = userDetailsService.getDetailsBasedOnUsernameOrEmailId(username, emailId);

		logger.info("Exited UserRegisterController ; getUserDetailsBasedOnUsernameOrEemailId method : {}",
				System.currentTimeMillis());

		return new ResponseEntity<>(userResponse, HttpStatus.OK);

	}

	// Path variable are mandatory
	/**
	 * 
	 * @param location
	 * @param availablity
	 * @param isLocation
	 * @return
	 */
	@ApiOperation(value = "Fetches the details of all the delivery agent who are available at a given location", response = User.class)
	@GetMapping("/search")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "User login successful", response = UserResponse.class) })
	public ResponseEntity<List<UserResponse>> filterUsers(
			@RequestParam(name = "location", required = false, defaultValue = "") String location,
			@RequestParam(name = "availablity", required = false, defaultValue = "") String availablity,
			@RequestParam(name = "isLocation", required = false, defaultValue = "false") boolean isLocation) {
		List<UserResponse> usersList = new ArrayList<>();
		logger.info("Entered UserFilterController ;  filterUsers : {}", System.currentTimeMillis());
		if (isLocation) {

			usersList = userFilterService.filterUsers(location, availablity);
		} else {
			location = "";
			usersList = userFilterService.filterUsers(location, availablity);
		}

		logger.info("Exited UserFilterController ; filterUsers : {} ", System.currentTimeMillis());

		return new ResponseEntity<>(usersList, HttpStatus.OK);

	}

	/*
	 * It should have been a patch request
	 */
	@ApiOperation(value = "Update the availability status of an user", response = UserResponse.class)
	@GetMapping("/{userId}/{status}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Page not found"),
			@ApiResponse(code = 200, message = "User login successful", response = UserResponse.class) })
	public ResponseEntity<UserResponse> updateUserAvailabilityStatus(@PathVariable("userId") String userId,
			@PathVariable("status") String status) {

		logger.info("Entered UserAvailabilityUpdateController  Time:, {}", System.currentTimeMillis());
		UserResponse userResponse = UserDetailsUpdateService.updateUserAvailabilityStatus(userId, status);
		logger.info("Exited UserAvailabilityUpdateController Time: {}", System.currentTimeMillis());

		return new ResponseEntity<>(userResponse, HttpStatus.OK);

	}

}
