package app.controller;

import app.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import app.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.CalcService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
public class CalcController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalcController.class);

    private CalcService calcService;
    private UserRepository userRepository;


    public CalcController(CalcService calcService,
                          UserRepository userRepository) {
        this.calcService = calcService;
        this.userRepository = userRepository;
    }

    @PostMapping("/calc")
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

    @GetMapping("/search/")
    public ResponseEntity<List> findRequests(@RequestParam(name = "startDate") LocalDateTime startDate,
                                             @RequestParam(name = "endDate") LocalDateTime endDate,
                                             @RequestParam(name = "statement") String statement,
                                             @RequestParam(name = "userName") String userName) {
        SearchRequest request = new SearchRequest(startDate, endDate, statement, userName);
        List<SearchResponse> result = calcService.findRequests(request);
        if (!result.isEmpty()) {
            LOGGER.info("{} records received", result.size());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            LOGGER.info("No such requests");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
