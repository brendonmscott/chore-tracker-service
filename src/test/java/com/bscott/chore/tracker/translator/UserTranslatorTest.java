package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.LoginCredentials;
import com.bscott.chore.tracker.domain.Role;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.RegisterUserDto;
import com.bscott.chore.tracker.dto.RoleDto;
import com.bscott.chore.tracker.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UserTranslatorTest {

    private static final Integer USER1_ID = 12345;
    private static final Integer USER2_ID = 67890;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserTranslator userTranslator;
    private Role adminRole;
    private Role userRole;
    private RoleDto adminRoleDto;
    private RoleDto userRoleDto;

    @Before
    public void setup(){

        userTranslator = new UserTranslator(passwordEncoder);

        adminRole = new Role();
        adminRole.setName("admin");

        userRole = new Role();
        userRole.setName("user");

        adminRoleDto = new RoleDto();
        adminRoleDto.setName("admin");

        userRoleDto = new RoleDto();
        userRoleDto.setName("user");

//        when(passwordEncoder.encode("password1")).thenReturn("EncodedPassword1");
    }

    @Test
    public void testToDto(){

        User user = new User();
        user.setId(USER1_ID);
        user.setFirstName("Emery");
        user.setLastName("Scott");
        user.setBirthDate(LocalDate.parse("2016-04-09"));
        user.setMoneyEarned(BigDecimal.ZERO);
        user.setPointsEarned(100);

        user.setRoles(new ArrayList<>());
        user.getRoles().add(adminRole);
        user.getRoles().add(userRole);

        UserDto userDto = userTranslator.toDto(user);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getBirthDate().toString(), userDto.getBirthDate());
        assertEquals(user.getMoneyEarned(), userDto.getMoneyEarned());
        assertEquals(user.getPointsEarned(), userDto.getPointsEarned());
        assertEquals(2, userDto.getRoles().size());
        assertEquals("admin", userDto.getRoles().get(0).getName());
        assertEquals("user", userDto.getRoles().get(1).getName());

    }

    @Test
    public void testToDto_Null(){

        UserDto userDto = userTranslator.toDto(null);
        assertNull(userDto);
    }

    @Test
    public void testToDtos(){

        List<User> users = new ArrayList<>();

        User user1 = new User("Brendon", "Scott", LocalDate.parse("1975-12-19"),
        "userName1", "email1", "password1");
        user1.setId(USER1_ID);
        user1.setMoneyEarned(BigDecimal.ZERO);
        user1.setPointsEarned(0);
        user1.setRoles(new ArrayList<>());
        user1.getRoles().add(adminRole);
        user1.getRoles().add(userRole);
        users.add(user1);

        User user2 = new User("Emery", "Scott", LocalDate.parse("2005-04-09"),
                "userName2", "email2", "password2");
        user2.setId(USER2_ID);
        user2.setMoneyEarned(BigDecimal.ZERO);
        user2.setPointsEarned(100);
        user2.setRoles(new ArrayList<>());
        user2.getRoles().add(userRole);
        users.add(user2);

        List<UserDto> userDtos = userTranslator.toDtos(users);

        assertEquals(user1.getId(), userDtos.get(0).getId());
        assertEquals(user1.getFirstName(), userDtos.get(0).getFirstName());
        assertEquals(user1.getLastName(), userDtos.get(0).getLastName());
        assertEquals(user1.getBirthDate().toString(), userDtos.get(0).getBirthDate());
        assertEquals(user1.getMoneyEarned(), userDtos.get(0).getMoneyEarned());
        assertEquals(user1.getPointsEarned(), userDtos.get(0).getPointsEarned());
        assertEquals(2, userDtos.get(0).getRoles().size());
        assertEquals("admin", userDtos.get(0).getRoles().get(0).getName());
        assertEquals("user", userDtos.get(0).getRoles().get(1).getName());

        assertEquals(user2.getId(), userDtos.get(1).getId());
        assertEquals(user2.getFirstName(), userDtos.get(1).getFirstName());
        assertEquals(user2.getLastName(), userDtos.get(1).getLastName());
        assertEquals(user2.getBirthDate().toString(), userDtos.get(1).getBirthDate());
        assertEquals(user2.getMoneyEarned(), userDtos.get(1).getMoneyEarned());
        assertEquals(user2.getPointsEarned(), userDtos.get(1).getPointsEarned());
        assertEquals(1, userDtos.get(1).getRoles().size());
        assertEquals("user", userDtos.get(1).getRoles().get(0).getName());

    }

    @Test
    public void testToDtos_Null(){
        List<UserDto> userDtos = userTranslator.toDtos(null);
        assertTrue(userDtos.isEmpty());
    }

    @Test
    public void testToDtos_EmptyList(){
        List<UserDto> userDtos = userTranslator.toDtos(new ArrayList<>());
        assertTrue(userDtos.isEmpty());
    }

    @Test
    public void testToEntity(){

        UserDto userDto = new UserDto();
        userDto.setId(USER1_ID);
        userDto.setFirstName("Emery");
        userDto.setLastName("Scott");
        userDto.setBirthDate("2016-04-09");
        userDto.setMoneyEarned(BigDecimal.ZERO);
        userDto.setPointsEarned(100);
//        userDto.setPassword("password1");

        userDto.setRoles(new ArrayList<>());
        userDto.getRoles().add(adminRoleDto);
        userDto.getRoles().add(userRoleDto);

        User user = userTranslator.toEntity(userDto);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getBirthDate(), user.getBirthDate().toString());
        assertEquals(userDto.getMoneyEarned(), user.getMoneyEarned());
        assertEquals(userDto.getPointsEarned(), user.getPointsEarned());
//        assertEquals("EncodedPassword1", userDto.getPassword());
        assertEquals(2, user.getRoles().size());
        assertEquals("admin", user.getRoles().get(0).getName());
        assertEquals("user", user.getRoles().get(1).getName());
    }

    @Test
    public void testToEntity_Null(){
        User user = userTranslator.toEntity(null);
        assertNull(user);
    }

    @Test
    public void testGetLoginCredentials(){

        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setFamilyName("Scott Family");
        registerUserDto.setFirstName("Brendon");
        registerUserDto.setLastName("Scott");
        registerUserDto.setBirthDate("1975-12-19");
        registerUserDto.setEmail("brendonscott@invalid.com");
        registerUserDto.setPassword("password");

        LoginCredentials loginCredentials = userTranslator.getLoginCredentials(registerUserDto);

        assertEquals(registerUserDto.getEmail(), loginCredentials.getEmail());
        assertEquals(registerUserDto.getPassword(), loginCredentials.getPassword());
        assertNull(loginCredentials.getId());
        assertNull(loginCredentials.getUsername());
    }

    @Test
    public void testGetLoginCredentials_Null() {

        LoginCredentials loginCredentials = userTranslator.getLoginCredentials(null);
        assertNull(loginCredentials);
    }
}
