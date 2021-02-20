package com.xyz.user.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum Roles {

	ADMIN("A"), 
	DELIVERY_AGENT("D");

	private String role;

	Roles(String currRole) {
		this.role = currRole;
	}

}
