package com.bscott.chore.tracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserDto {

    @NotBlank
    private String familyName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Past
    private String birthDate;
    @Email
    private String email;
    @NotBlank
    private String password;
}
