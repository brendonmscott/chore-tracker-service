package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class Reward {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Integer rewardValue;
    @Column
    private Integer ownerId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(
            name = "reward_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "assignee_id",
                    referencedColumnName = "id"
            )
    )
    private List<User> assignees;
}
