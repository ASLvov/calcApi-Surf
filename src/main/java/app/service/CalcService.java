package app.service;

import app.utils.ReversePolishNotation;
import app.domain.CalcRequest;
import app.domain.CalcResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalcService {

    public CalcResponse calculate(CalcRequest request) {
        CalcResponse calcResponse = new CalcResponse(ReversePolishNotation.eval(request.getStatement()).toString());
        return calcResponse;
    }
}
