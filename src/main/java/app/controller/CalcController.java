package app.controller;

import app.domain.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
public class CalcController {


    private final CalcService calcService;

    @PostMapping("/calc")
    public ResponseEntity<CalcResponse> calculate(@RequestBody CalcRequest request) {
        try {
            CalcResponse calcResponse = calcService.calculate(request);
            return new ResponseEntity<>(calcResponse, HttpStatus.OK);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/search/")
    public ResponseEntity<List> findRequests(@RequestParam(name = "startDate") LocalDateTime startDate,
                                             @RequestParam(name = "endDate") LocalDateTime endDate,
                                             @RequestParam(name = "statement") String statement,
                                             @RequestParam(name = "userName") String userName) {
        SearchRequest request = new SearchRequest();
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setStatement(statement);
        request.setUserName(userName);
        List<SearchResponse> result = calcService.findRequests(request);
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
