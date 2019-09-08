package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginCredentials, Integer> {

    LoginCredentials findByEmailAndPassword(String email, String password);
    Optional<LoginCredentials> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
