package com.bscott.chore.tracker.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private String id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Past
    private String birthDate;
    @NotEmpty
    private Set<String> roles = new HashSet<>();
    private Integer pointsEarned;
    private BigDecimal moneyEarned;
}
