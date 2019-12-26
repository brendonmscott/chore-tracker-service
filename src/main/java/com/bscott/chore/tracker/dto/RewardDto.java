package com.bscott.chore.tracker.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RewardDto {

    private Integer id;
    private String name;
    private String description;
    private List<UserDto> assignees;
    private Integer rewardValue;
    @NotEmpty
    private Integer ownerId;
}
