package com.bscott.chore.tracker.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotEmpty
    private String firstName;
    @Column
    @NotEmpty
    private String lastName;
    @Column
    @NotNull
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate birthDate;
    @Column
    private Integer pointsEarned;
    @Column
    private BigDecimal moneyEarned;
    @NotEmpty
    @Email
    @Column
    private String email;
    @NotEmpty
    @Column
    private String username;
    @NotEmpty
    @Column
    private String password;
    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id")
    )
    private List<Role> roles;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "family_member_id",
                    referencedColumnName = "id"
            )
    )
    private List<User> familyMembers;

    public User(){
    }

    public User(String firstName, String lastName, LocalDate birthDate, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
 }
