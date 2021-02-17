package app.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchRequest {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String statement;

    private String userName;
}