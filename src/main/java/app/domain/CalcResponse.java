package app.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class CalcResponse {

    @NonNull
    private String result;
}