package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.LoginCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRepository extends MongoRepository<LoginCredentials, String> {

    LoginCredentials findByEmailAndPassword(String email, String password);
}
