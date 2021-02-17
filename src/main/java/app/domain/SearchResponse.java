package app.domain;

import app.converter.LocalDateStringConverter;
import app.converter.StringLocalDateConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchResponse {

    private Long id;

    @JsonSerialize(converter = LocalDateStringConverter.class)
    @JsonDeserialize(converter = StringLocalDateConverter.class)
    private LocalDateTime localDateTime;

    private String statement;

    private String result;

    private Long userId;

    private String userName;
}
