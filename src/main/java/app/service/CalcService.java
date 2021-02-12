package app.service;

import app.algorithm.ReversePolishNotation;
import app.domain.CalcResponse;
import org.springframework.stereotype.Service;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalcService {

    public List<CalcResponse> calculate(String statement) {
        List<CalcResponse> list = new ArrayList<>();
        list.add(new CalcResponse(ReversePolishNotation.eval(statement)));
        return list;
    }
}
