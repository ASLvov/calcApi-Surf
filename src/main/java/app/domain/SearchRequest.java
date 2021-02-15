package app.domain;

import app.converter.LocalDateStringConverter;
import app.converter.StringLocalDateConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class SearchRequest {
    @JsonSerialize(converter = LocalDateStringConverter.class)
    @JsonDeserialize(converter = StringLocalDateConverter.class)
    private LocalDateTime startDate;
    @JsonSerialize(converter = LocalDateStringConverter.class)
    @JsonDeserialize(converter = StringLocalDateConverter.class)
    private LocalDateTime endDate;
    private String statement;
    private String userName;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
