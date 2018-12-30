package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BannerMessage {

    @Id
    private String id;
    private String headlineOne;
    private String headlineOneText;
    private String headlineTwo;
    private String headlineTwoText;
    private String headlineThree;
    private String headlineThreeText;
}
