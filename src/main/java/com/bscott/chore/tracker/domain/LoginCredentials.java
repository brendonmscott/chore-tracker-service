package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class LoginCredentials {

    @Id
    private String id;
    private String userId;
    private String email;
    private String password;
}
