package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@DynamicUpdate
public class Frequency {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String type;

    @Column
    private boolean monday;

    @Column
    private boolean tuesday;

    @Column
    private boolean wednesday;

    @Column
    private boolean thursday;

    @Column
    private boolean friday;

    @Column
    private boolean saturday;

    @Column
    private boolean sunday;
}
