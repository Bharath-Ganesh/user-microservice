package com.xyz.user.entity.request.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import com.xyz.user.model.request.UserLoginRequest;

public class UserLoginRequestTest {

	@Test
	public void testUserLoginRequest() {
		UserLoginRequest userLoginRequest = new UserLoginRequest();
		userLoginRequest.setPassword("1234");
		userLoginRequest.setUsername("XYZ");
		assertEquals(userLoginRequest.getPassword(), "1234");
		assertEquals(userLoginRequest.getUsername(), "XYZ");
		assertNotNull(userLoginRequest);
	}

}
