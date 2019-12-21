package com.bscott.chore.tracker.translator;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;

public class LocalDateConverter extends BidirectionalConverter<LocalDate, String> {

   @Override
   public String convertTo(LocalDate source, Type<String> destinationType) {

      return source == null ? null : source.toString();
   }

   @Override
   public LocalDate convertFrom(String source, Type<LocalDate> destinationType) {

      return source == null ? null : LocalDate.parse(source);
   }
}
