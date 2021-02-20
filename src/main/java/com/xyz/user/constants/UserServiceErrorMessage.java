package com.xyz.user.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum UserServiceErrorMessage {

	PASSWORD_INVALID("Password entered is invalid"), USER_NOT_FOUND("User does not exist"),
	INVALID_USERNAME_FORMAT("Username is not in correct format");
	;

	private String message;

	UserServiceErrorMessage(String errorMessage) {
		this.message = errorMessage;
	}

}
