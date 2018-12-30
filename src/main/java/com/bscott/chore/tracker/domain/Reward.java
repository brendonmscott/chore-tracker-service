package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Reward {

    @Id
    private String id;
    private String name;
    private String description;
    private RewardType rewardType;
    private Integer rewardValue;
}
