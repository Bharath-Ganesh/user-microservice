package com.movie.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.user.model.User;
import com.movie.user.service.CreateUserService;

@RestController
public class UserController {

	@Autowired
	private CreateUserService createUserService;

	@PostMapping("/create")
	public String createUser(@RequestBody User user) {

		String message=createUserService.createUser(user);
		return message;

	}

}
