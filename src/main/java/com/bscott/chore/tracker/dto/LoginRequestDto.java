package com.bscott.chore.tracker.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginRequestDto {

    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    private String password;
}
