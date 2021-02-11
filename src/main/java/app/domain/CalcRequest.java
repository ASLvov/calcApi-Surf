package app.domain;

public class CalcRequest {
    private String statement;

    public CalcRequest(String statement) {
        this.statement = statement;
    }

    public CalcRequest() {
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
