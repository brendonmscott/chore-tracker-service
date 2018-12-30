package com.bscott.chore.tracker.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountDto {

    private String id;
    private String familyName;
    private UserDto owner;
    private List<UserDto> familyMembers = new ArrayList<>();
}
