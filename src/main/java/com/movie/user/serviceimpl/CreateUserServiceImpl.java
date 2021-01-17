package com.movie.user.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.user.entity.UserEntity;
import com.movie.user.model.User;
import com.movie.user.repository.CreateUserRepository;
import com.movie.user.service.CreateUserService;

@Service
public class CreateUserServiceImpl implements CreateUserService {

	@Autowired
	private CreateUserRepository createUserRepository;

	// Convert user into Entity
	public UserEntity convertIntoEntity(User user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setAddress(user.getAddress());
		userEntity.setEmail_id(user.getEmail_id());
		userEntity.setId(20);
		userEntity.setUsername(user.getName());
		userEntity.setPassword(user.getPassword());
		return userEntity;

	}

	@Override
	public String createUser(User user) {
		// TODO Auto-generated method stub
		String message = "User exist";
		if (user.getId() != null) {
			Optional<UserEntity> userExist = createUserRepository.findById(user.getId());
			if (userExist.isEmpty()) {
				UserEntity userEntity = convertIntoEntity(user);
				try {
					createUserRepository.save(userEntity);
				} catch (Exception ex) {
					System.out.println("Error");
				}

				message = "User created successfully";
			}
		}
		return message;
	}

}