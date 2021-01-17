package com.movie.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.user.entity.UserEntity;

@Repository
public interface CreateUserRepository extends JpaRepository<UserEntity, Integer>{

}
