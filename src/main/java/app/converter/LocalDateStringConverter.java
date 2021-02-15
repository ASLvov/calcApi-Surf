package app.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateStringConverter extends StdConverter<LocalDateTime, String> {

    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSS";

    @Override
    public String convert(LocalDateTime localDateTime) {
        return localDateTime == null ? "" : localDateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
