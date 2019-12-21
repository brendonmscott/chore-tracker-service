package com.bscott.chore.tracker.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, String> {

    @Override
    public String convertToDatabaseColumn(LocalDate locDate) {
        return locDate == null ? null : locDate.toString();
    }
 
    @Override
    public LocalDate convertToEntityAttribute(String date) {
        return date == null ? null : LocalDate.parse(date);
    }
}
