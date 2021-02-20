package com.xyz.user.entity.response.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.xyz.user.model.response.UserResponse;

public class UserResponseTest {

	@Test
	void testUserResponseTest() {
		UserResponse userResponse = new UserResponse();
		userResponse.setMessage("SUCCESS");
		assertEquals(userResponse.getMessage(), "SUCCESS");
		assertNotNull(userResponse);
	}

}
