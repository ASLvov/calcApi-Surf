package app.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringLocalDateConverter extends StdConverter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String s) {
        if (s == null || s.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(LocalDateStringConverter.DATE_FORMAT));
    }
}
