package com.xyz.user.entity.request.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

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

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
