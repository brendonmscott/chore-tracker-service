package com.bscott.chore.tracker.dto;

import lombok.Data;

@Data
public class RewardDto {

    private String id;
    private String name;
    private String description;
    private String rewardType;
    private Integer rewardValue;
}
