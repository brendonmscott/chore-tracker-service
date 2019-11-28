package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.LoginCredentials;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.exception.NullEntityException;
import com.bscott.chore.tracker.exception.UserAlreadyExistsException;
import com.bscott.chore.tracker.exception.UserNotFoundException;
import com.bscott.chore.tracker.repository.LoginRepository;
import com.bscott.chore.tracker.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User findUser(String username) {

        LoginCredentials loginCredentials = loginRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return loginCredentials.getUser();
    }

    public User registerNewUser(User user) {

        if (loginRepository.existsByUsername(user.getLoginCredentials().getUsername())) {
            throw new UserAlreadyExistsException("Username " + user.getLoginCredentials().getUsername() + " already in use!");
        }

        User result = userRepository.save(user);
        result.getLoginCredentials().setUser(result);
        loginRepository.save(result.getLoginCredentials());
        return user;
    }

    public User addFamilyMember(Integer userId, User familyMember) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        familyMember.getLoginCredentials().setPassword(passwordEncoder.encode(familyMember.getLoginCredentials().getPassword()));
        User newFamilyMember = registerNewUser(familyMember);

        if (user.getFamilyMembers() == null) {
            user.setFamilyMembers(new ArrayList<>());
        }
        user.getFamilyMembers().add(newFamilyMember);
        userRepository.save(user);

        return user;
    }

    public void removeFamilyMember(Integer userId, Integer familyMemberId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        deleteUser(familyMemberId);

        user.getFamilyMembers().removeIf(x -> x.getId().equals(familyMemberId));
        userRepository.save(user);
    }

    public User updateFamilyMember(Integer userId, User familyMember) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        updateUser(familyMember);

        user.getFamilyMembers().removeIf(x -> x.getId().equals(familyMember.getId()));
        user.getFamilyMembers().add(familyMember);
        userRepository.save(user);

        return user;
    }

    public User updateUser(User user) {

        if (user == null) {
            throw new NullEntityException(this.getClass().getName());
        }

        userRepository.save(user);
        return user;
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
