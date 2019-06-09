package com.bscott.chore.tracker.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RoleDto {

    @NotNull
    private Integer id;
    @NotEmpty
    private String name;
}
