package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginCredentials, Integer> {

    LoginCredentials findByUsernameAndPassword(String username, String password);
    Optional<LoginCredentials> findByUsername(String username);
    boolean existsByUsername(String username);
}
