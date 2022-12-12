package com.sunil.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunil.userservice.entites.User;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
