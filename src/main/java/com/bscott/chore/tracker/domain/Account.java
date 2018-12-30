package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class Account {

    @Id
    private String id;
    private String familyName;
    private User owner;
    private List<User> familyMembers = new ArrayList<>();
}
