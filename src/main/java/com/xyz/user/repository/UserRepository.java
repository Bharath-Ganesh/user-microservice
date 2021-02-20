package com.xyz.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.xyz.user.entity.User;

//import com.movie.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// Optional<User> findByUsernameAndRole(String username, String role);

	Optional<User> findByUsername(String username);

	Optional<List<User>> findByAddressAndRole(String location, String role);

	Optional<List<User>> findByAvailablityAndRole(String availablity, String role);

	Optional<List<User>> findByAddressAndAvailablityAndRole(String location, String availablity, String role);

	Optional<User> findByEmailId(String emailId);

}
