package com.bscott.chore.tracker.translator;

import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

// TODO: Replace Joda with java.time
public class LocalDateConverter extends BidirectionalConverter<LocalDate, String> {

   @Override
   public String convertTo(LocalDate source, Type<String> destinationType) {
      return source.toString("MM/dd/YYYY");
   }

   @Override
   public LocalDate convertFrom(String source, Type<LocalDate> destinationType) {

      DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/YYYY");
      return dtf.parseLocalDate(source);
   }
}
