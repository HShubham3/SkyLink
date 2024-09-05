package com.scm.skylink.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.skylink.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

}
