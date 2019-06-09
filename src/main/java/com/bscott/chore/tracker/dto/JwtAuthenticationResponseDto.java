package com.bscott.chore.tracker.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {

    private Integer id;
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponseDto(Integer userId, String accessToken) {
        this.id = userId;
        this.accessToken = accessToken;
    }
}
