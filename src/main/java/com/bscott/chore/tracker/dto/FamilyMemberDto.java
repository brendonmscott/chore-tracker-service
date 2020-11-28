package com.bscott.chore.tracker.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FamilyMemberDto {

    @NotNull
    private UserDto user;
    @NotNull
    private CredentialsDto credentials;
}
