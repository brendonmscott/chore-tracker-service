package com.bscott.chore.tracker.translator;

import ma.glasnost.orika.metadata.Type;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocalDateConverterTest {

    private LocalDateConverter localDateConverter = new LocalDateConverter();

    @Test
    public void testConvertTo(){

        Type<String> destinationType = null;
        String date = localDateConverter.convertTo(new LocalDate("2016-04-09"), destinationType);

        assertEquals("2016-04-09", date);
    }

    @Test
    public void testConvertFrom(){

        Type<LocalDate> destinationType = null;
        LocalDate date = localDateConverter.convertFrom("2016-04-09", destinationType);

        assertEquals("2016-04-09", date.toString());
    }
}
