package app.domain;

import app.converter.LocalDateStringConverter;
import app.converter.StringLocalDateConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class SearchResponse {
    private Long id;
    @JsonSerialize(converter = LocalDateStringConverter.class)
    @JsonDeserialize(converter = StringLocalDateConverter.class)
    private LocalDateTime localDateTime;
    private String statement;
    private String result;
    private Long userId;
    private String userName;

    public SearchResponse(Long id, LocalDateTime localDateTime, String statement, String result, Long userId, String userName) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.statement = statement;
        this.result = result;
        this.userId = userId;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
