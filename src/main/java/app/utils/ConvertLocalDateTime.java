package app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ConvertLocalDateTime {
    public static LocalDateTime convertStartDate (LocalDateTime date) {
        return date == null ? LocalDateTime.of(0, 1, 1, 0, 0, 0, 0) : date;
    }
    public static LocalDateTime convertEndDate (LocalDateTime date) {
        return date == null ? LocalDateTime.now() : date;
    }
}
