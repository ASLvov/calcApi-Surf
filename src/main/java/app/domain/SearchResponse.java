package app.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchResponse {

    private Long id;

    private LocalDateTime localDateTime;

    private String statement;

    private String result;

    private Long userId;

    private String userName;
}
