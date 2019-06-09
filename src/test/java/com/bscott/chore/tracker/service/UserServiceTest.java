package com.bscott.chore.tracker.service;

import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private static final Integer USER_ID = 12345;
    private static final Integer FAMILY_MEMBER_ID = 67890;

    @InjectMocks
    private UserService userService = new UserService();

    @Mock
    private UserRepository userRepository;

    private User user;
    private User familyMember;

    @Before
    public void setup(){

        user = new User("Brendon", "Scott", LocalDate.parse("1975-12-19"),
                "bscott", "brendon@nowhere.com", "password1");

        familyMember = new User("Emery", "Scott", LocalDate.parse("2005-04-09"),
                "escott", "emery@nowhere.com", "password2");
    }

    @Test
    public void updateFamilyMember(){

        user.setId(USER_ID);
        user.setFamilyMembers(new ArrayList<>());
        user.getFamilyMembers().add(familyMember);

        familyMember.setId(FAMILY_MEMBER_ID);

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.save(familyMember)).thenReturn(familyMember);

        User familyMember = userService.updateFamilyMember(USER_ID, user);

    }
}
