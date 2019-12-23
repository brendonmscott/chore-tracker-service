package com.bscott.chore.tracker.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ChoreDto {

    private Integer id;
    @NotEmpty
    private String name;
    private String description;
    private List<UserDto> assignees;
    @NotNull
    @Valid
    private FrequencyDto frequency;
    private LocalDate expireDate;
    private boolean completed;
    private boolean approved;
    private BigDecimal monetaryValue;
    @NotEmpty
    private Integer ownerId;

}
