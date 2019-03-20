package com.bscott.chore.tracker.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {

    private String id;
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponseDto(String userId, String accessToken) {
        this.id = userId;
        this.accessToken = accessToken;
    }
}
