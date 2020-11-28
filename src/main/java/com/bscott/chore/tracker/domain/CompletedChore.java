package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
@DynamicUpdate
public class CompletedChore {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate completedDate;
    @Column
    private boolean approved;
    @ManyToOne
    private Chore chore;
    @Column
    private Integer assigneeId;
}
