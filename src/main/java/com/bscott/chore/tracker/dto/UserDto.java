package com.bscott.chore.tracker.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    private String id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Past
    private String birthDate;
    @NotEmpty
    private Set<String> roles;
    private List<UserDto> familyMembers;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Integer pointsEarned;
    private BigDecimal moneyEarned;
}
