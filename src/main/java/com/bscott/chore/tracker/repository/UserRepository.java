package com.bscott.chore.tracker.repository;

import com.bscott.chore.tracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findUsersByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findById(Integer id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
