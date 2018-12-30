package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Integer pointsEarned;
    private BigDecimal moneyEarned;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Set<String> roles = new HashSet<>();

    public User(){
    }

    public User(String firstName, String lastName, LocalDate birthDate, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
