package com.bscott.chore.tracker.dto;

import lombok.Data;

@Data
public class ChoreDto {

    private String id;
    private String name;
    private String description;
    private UserDto assignee;
    private String frequency;
    private RewardDto reward;
}
