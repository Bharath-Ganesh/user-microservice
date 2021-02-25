package com.xyz.user.entity.response.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


import com.xyz.user.model.response.UserResponse;

public class UserResponseTest {

	@Test
	public void testUserResponseTest() {
		UserResponse userResponse = new UserResponse();
		userResponse.setMessage("SUCCESS");
		assertEquals(userResponse.getMessage(), "SUCCESS");
		assertNotNull(userResponse);
	}

}
