package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

//    Optional<User> findByUsernameOrEmail(String username, String email);
//    Optional<User> findById(Integer id);
//    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email);
}
