package com.xyz.user.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This class is used to store the user login request
 */
@NoArgsConstructor
@Getter
@Setter
public class UserLoginRequest {

	@ApiModelProperty(value = "Username of a user")
	private String username;

	@ApiModelProperty(value = "Password of a user")
	private String password;
	

}
