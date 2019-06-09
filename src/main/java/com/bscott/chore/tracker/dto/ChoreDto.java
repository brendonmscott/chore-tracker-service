package com.bscott.chore.tracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChoreDto {

    private Integer id;
    private String name;
    private String description;
    private List<UserDto> assignees;
    private String frequency;
    private RewardDto reward;
}
