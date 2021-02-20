package com.xyz.user.model.error;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Error implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9179943472454563706L;

	private String errorCode;
	private String errorMessage;

}
