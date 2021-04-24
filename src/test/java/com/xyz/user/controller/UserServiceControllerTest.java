package com.xyz.user.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.request.UserLoginRequest;
import com.xyz.user.model.response.UserResponse;
import com.xyz.user.service.UserLoginInService;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceControllerTest {

	@InjectMocks
	UserServiceController userServiceController;

	@Mock
	UserLoginInService userLoginInService;

	@Test
	public void testLoginIn() throws UserServiceException {
		when(userLoginInService.loginIn(Mockito.any())).thenReturn(new UserResponse());
		ResponseEntity<UserResponse> userResponse = userServiceController.loginIn(new UserLoginRequest());
		assertNotNull("testLoginIn", userResponse);

	}

}
