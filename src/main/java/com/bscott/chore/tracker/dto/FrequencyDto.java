package com.bscott.chore.tracker.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FrequencyDto {

    @NotEmpty
    private String type;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
}
