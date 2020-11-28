package com.bscott.chore.tracker.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.List;

@Data
public class UserDto {

    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Past
    private String birthDate;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private List<RoleDto> roles;
    private List<UserDto> familyMembers;
    private Integer pointsEarned;
    private BigDecimal savings;
    private BigDecimal wallet;
}
