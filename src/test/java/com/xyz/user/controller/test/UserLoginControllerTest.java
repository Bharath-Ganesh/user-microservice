//package com.xyz.user.controller.test;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import com.xyz.user.controller.UserServiceController;
//import com.xyz.user.exception.UserServiceException;
//import com.xyz.user.model.request.UserLoginRequest;
//import com.xyz.user.model.response.UserResponse;
//import com.xyz.user.service.UserLoginInService;
//
//@RunWith(SpringRunner.class)
//@ExtendWith(MockitoExtension.class)
//public class UserLoginControllerTest {
//
//	protected MockMvc mvc;
//
//	@InjectMocks
//	private UserServiceController userServiceController;
//
//	@Mock
//	private UserLoginInService loginInService;
//
//	@Test
//	public void testLogin() throws UserServiceException {
//		UserResponse userResponse = new UserResponse();
//		UserLoginRequest userLoginRequest = new UserLoginRequest();
//		userLoginRequest.setPassword("MTI0NA==");
//		userLoginRequest.setUsername("xyz65");
//		when(loginInService.loginIn(userLoginRequest)).thenReturn(userResponse);
//		assertNotNull(userLoginRequest);
//
//	}
//
//}
