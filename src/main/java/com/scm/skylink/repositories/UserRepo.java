package com.scm.skylink.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.skylink.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

}
