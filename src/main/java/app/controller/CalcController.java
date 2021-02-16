package app.controller;

import app.domain.CalcRequest;
import app.domain.CalcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import app.domain.UserRequest;
import app.repository.UserRepository;
import app.repository.UserRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.CalcService;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/calc")
public class CalcController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalcController.class);

    private CalcService calcService;


    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    @PostMapping
    public ResponseEntity<CalcResponse> calculate(@RequestBody CalcRequest request) {
        try {
            CalcResponse calcResponse = calcService.calculate(request);
            LOGGER.info("Got request with statement '{}', the result is '{}'", request.getStatement(), calcResponse.getResult());
            return new ResponseEntity<>(calcResponse, HttpStatus.OK);
        } catch (NumberFormatException exception) {
            LOGGER.info("Incorrect statement entered!");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
