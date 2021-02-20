package com.xyz.user.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private String message;
	private Integer userId;
	private String name;
	private String emailId;
	private String address;
	private String availability;
	private String role;

}
