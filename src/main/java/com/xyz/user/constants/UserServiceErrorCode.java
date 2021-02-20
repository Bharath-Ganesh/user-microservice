package com.xyz.user.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum UserServiceErrorCode {

	PASSWORD_INVALID("USER_101"), 
	USER_NOT_FOUND("USER_102"),
	INVALID_USERNAME_FORMAT("USER_103");

	private String code;

	UserServiceErrorCode(String errorCode) {
		this.code = errorCode;
	}

}
