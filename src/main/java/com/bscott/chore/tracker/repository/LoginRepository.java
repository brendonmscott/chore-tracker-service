package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginCredentials, Integer> {

    LoginCredentials findByEmailAndPassword(String email, String password);
}
