package com.bscott.chore.tracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequestDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private String birthDate;
    @NotEmpty
    private String userName;
    @NotNull
    private CredentialsDto credentials;

    public String getName() {
        return firstName + " " + lastName;
    }
}
