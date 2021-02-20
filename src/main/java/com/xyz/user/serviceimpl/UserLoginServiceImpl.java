package com.xyz.user.serviceimpl;

import java.util.Base64;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xyz.user.constants.UserServiceErrorMessage;
import com.xyz.user.constants.UserServiceErrorCode;
import com.xyz.user.entity.User;
import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.request.UserLoginRequest;
import com.xyz.user.model.response.UserResponse;
import com.xyz.user.repository.UserRepository;
import com.xyz.user.service.UserLoginInService;

@Service
public class UserLoginServiceImpl implements UserLoginInService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponse loginIn(UserLoginRequest userLoginRequest) throws UserServiceException {

		UserResponse userResponse = null;
		if (null != userLoginRequest) {

			if (userLoginRequest.getUsername() != null) {

				// Optional<User> userExist =
				// userRepository.findByUsernameAndRole(userLoginRequest.getUsername(),
				// userLoginRequest.getRole());
				Optional<User> userExist = userRepository.findByUsername(userLoginRequest.getUsername());
				userResponse = new UserResponse();
				if (userExist.isPresent()) {
					Base64.Decoder decoder = Base64.getDecoder();
					String password = new String(decoder.decode(userExist.get().getPassword()));

					if (userLoginRequest.getPassword().equals(password)) {
						userResponse.setMessage("Login Successfully");
						userResponse.setRole(userExist.get().getRole());
					} else {
						throw new UserServiceException(UserServiceErrorCode.PASSWORD_INVALID.getCode(),
								UserServiceErrorMessage.PASSWORD_INVALID.getMessage(), HttpStatus.BAD_REQUEST, null);
					}

				} else {
					throw new UserServiceException(UserServiceErrorCode.USER_NOT_FOUND.getCode(),
							UserServiceErrorMessage.USER_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST, null);
				}
			}

		}
		return userResponse;

	}

}
