package com.xyz.user.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.xyz.user.entity.User;
import com.xyz.user.exception.UserServiceException;
import com.xyz.user.model.response.UserResponse;
import com.xyz.user.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
public class UserDetailsServiceImplTest {

	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Mock
	private UserRepository userRepository;

	public User createUser() {

		User user = new User();
		user.setAddress("address");
		user.setUserId(1);
		user.setPassword("password");
		user.setName("name");
		user.setUsername("username");
		return user;
	}

	@Test
	public void testConvertEntityIntoResponse() {

		User user = createUser();
		UserResponse userResponse = userDetailsServiceImpl.convertEntityIntoResponse(user);
		assertNotNull("User response", userResponse);

	}

	@Test(expected = UserServiceException.class)
	public void testGetUserDetails() throws UserServiceException {
		String userId = "1";
		userDetailsServiceImpl.getUserDetails(userId);

	}

	@Test(expected = UserServiceException.class)
	public void testGetUserDetailsEmptyUserId() throws UserServiceException {
		String userId = "";
		userDetailsServiceImpl.getUserDetails(userId);

	}

	@Test
	public void testGetUserDetailsRepo() throws UserServiceException {
		String userId = "1";
		User user = createUser();
		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		UserResponse userResponse = userDetailsServiceImpl.getUserDetails(userId);
		assertNotNull("User response", userResponse);

	}

}
