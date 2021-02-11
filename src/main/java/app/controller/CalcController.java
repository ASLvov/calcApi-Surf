package app.controller;

import app.domain.CalcRequest;
import app.domain.CalcResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import app.service.CalcService;

import java.util.List;

@Controller
@RequestMapping(path = "/calc")
public class CalcController {
    @Autowired
    private CalcService calcService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CalcResponse> calculate(@RequestBody CalcRequest request) throws Exception {
        return calcService.calculate(request.getStatement());
    }
}
