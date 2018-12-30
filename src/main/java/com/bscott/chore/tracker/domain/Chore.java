package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Chore {

    @Id
    private String id;
    private String name;
    private String description;
    private User assignee;
    private FrequencyType frequency;
    private Reward reward;
}
