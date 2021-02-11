package app.service;

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
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
        List<CalcResponse> list = new ArrayList<>();
        try {
            list.add(new CalcResponse(engine.eval(statement).toString()));
            return list;
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        list.add(new CalcResponse("Error"));
        return list;
    }
}
