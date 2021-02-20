package com.xyz.user.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationRequest {

	private String username;
	private String name;
	private String address;
	private String emailId;
	private String password;
	private String confirmPassword;
	private String role;

}
