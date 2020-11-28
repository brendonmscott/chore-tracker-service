package com.bscott.chore.tracker.dto;

import lombok.Data;

@Data
public class CompletedChoreDto {

    private Integer id;
    private String completedDate;
    private boolean approved;
    private ChoreDto chore;
    private Integer assigneeId;
}
