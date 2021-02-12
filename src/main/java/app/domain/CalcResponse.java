package app.domain;

public class CalcResponse {

    private Long result;

    public CalcResponse(Long result) {
        this.result = result;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }
}
