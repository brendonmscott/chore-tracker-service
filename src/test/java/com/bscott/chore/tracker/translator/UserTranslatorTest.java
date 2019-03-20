package com.bscott.chore.tracker.translator;

import com.bscott.chore.tracker.domain.LoginCredentials;
import com.bscott.chore.tracker.domain.User;
import com.bscott.chore.tracker.dto.RegisterUserDto;
import com.bscott.chore.tracker.dto.UserDto;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserTranslatorTest {

    private UserTranslator userTranslator = new UserTranslator();

    @Test
    public void testToDto(){

        User user = new User();
        user.setId("583a9be33004dfd16b956697");
        user.setFirstName("Emery");
        user.setLastName("Scott");
        user.setBirthDate(new LocalDate("2016-04-09"));
        user.setMoneyEarned(BigDecimal.ZERO);
        user.setPointsEarned(100);
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("user");
        user.setRoles(roles);

        UserDto userDto = userTranslator.toDto(user);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getBirthDate().toString(), userDto.getBirthDate());
        assertEquals(user.getMoneyEarned(), userDto.getMoneyEarned());
        assertEquals(user.getPointsEarned(), userDto.getPointsEarned());
        assertEquals(2, userDto.getRoles().size());
        assertTrue(userDto.getRoles().contains("admin"));
        assertTrue(userDto.getRoles().contains("user"));

    }

    @Test
    public void testToDto_Null(){

        UserDto userDto = userTranslator.toDto(null);
        assertNull(userDto);
    }

    @Test
    public void testToDtos(){

        List<User> users = new ArrayList<>();

        User user1 = new User("Brendon", "Scott", new LocalDate("1975-12-19"),
        "userName1", "email1", "password1");
        user1.setId("583a9be33004dfd16b956697");
        user1.setMoneyEarned(BigDecimal.ZERO);
        user1.setPointsEarned(0);
        Set<String> roles1 = new HashSet<>();
        roles1.add("admin");
        roles1.add("user");
        user1.setRoles(roles1);
        users.add(user1);

        User user2 = new User("Emery", "Scott", new LocalDate("2005-04-09"),
                "userName2", "email2", "password2");
        user2.setId("583a9be33004dfd16b956698");
        user2.setMoneyEarned(BigDecimal.ZERO);
        user2.setPointsEarned(100);
        Set<String> roles2 = new HashSet<>();
        roles2.add("user");
        user2.setRoles(roles2);
        users.add(user2);

        List<UserDto> userDtos = userTranslator.toDtos(users);

        assertEquals(user1.getId(), userDtos.get(0).getId());
        assertEquals(user1.getFirstName(), userDtos.get(0).getFirstName());
        assertEquals(user1.getLastName(), userDtos.get(0).getLastName());
        assertEquals(user1.getBirthDate().toString(), userDtos.get(0).getBirthDate());
        assertEquals(user1.getMoneyEarned(), userDtos.get(0).getMoneyEarned());
        assertEquals(user1.getPointsEarned(), userDtos.get(0).getPointsEarned());
        assertEquals(2, userDtos.get(0).getRoles().size());
        assertTrue(userDtos.get(0).getRoles().contains("admin"));
        assertTrue(userDtos.get(0).getRoles().contains("user"));

        assertEquals(user2.getId(), userDtos.get(1).getId());
        assertEquals(user2.getFirstName(), userDtos.get(1).getFirstName());
        assertEquals(user2.getLastName(), userDtos.get(1).getLastName());
        assertEquals(user2.getBirthDate().toString(), userDtos.get(1).getBirthDate());
        assertEquals(user2.getMoneyEarned(), userDtos.get(1).getMoneyEarned());
        assertEquals(user2.getPointsEarned(), userDtos.get(1).getPointsEarned());
        assertEquals(1, userDtos.get(1).getRoles().size());
        assertTrue(userDtos.get(1).getRoles().contains("user"));

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
        userDto.setId("583a9be33004dfd16b956697");
        userDto.setFirstName("Emery");
        userDto.setLastName("Scott");
        userDto.setBirthDate("2016-04-09");
        userDto.setMoneyEarned(BigDecimal.ZERO);
        userDto.setPointsEarned(100);
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("user");
        userDto.setRoles(roles);

        User user = userTranslator.toEntity(userDto);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getBirthDate(), user.getBirthDate().toString());
        assertEquals(userDto.getMoneyEarned(), user.getMoneyEarned());
        assertEquals(userDto.getPointsEarned(), user.getPointsEarned());
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains("admin"));
        assertTrue(user.getRoles().contains("user"));
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
        assertNull(loginCredentials.getUserId());
    }

    @Test
    public void testGetLoginCredentials_Null() {

        LoginCredentials loginCredentials = userTranslator.getLoginCredentials(null);
        assertNull(loginCredentials);
    }
}
