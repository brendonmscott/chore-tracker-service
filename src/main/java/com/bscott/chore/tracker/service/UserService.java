package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.exception.NullEntityException;
import com.bscott.chore.tracker.exception.UserNotFoundException;
import com.bscott.chore.tracker.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User findUser(String username, String email) {

        User u = new User();
        u.setUsername(username);
        u.setEmail(email);

        log.info("User to query: {}", u);
        Optional<User> user = userRepository.findOne(Example.of(u));

        return user.orElse(null);
    }

    public User addUser(User user) {

        userRepository.save(user);
        return user;
    }

    public User addFamilyMember(Integer userId, User familyMember) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        User newFamilyMember = addUser(familyMember);

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
