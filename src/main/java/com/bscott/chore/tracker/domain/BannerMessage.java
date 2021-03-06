package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate
public class BannerMessage {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String headlineOne;
    @Column
    private String headlineOneText;
    @Column
    private String headlineTwo;
    @Column
    private String headlineTwoText;
    @Column
    private String headlineThree;
    @Column
    private String headlineThreeText;
}
