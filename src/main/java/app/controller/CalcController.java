package app.controller;

import app.controller.dto.CalcRequest;
import app.controller.dto.CalcResponse;
import app.controller.dto.SearchRequest;
import app.controller.dto.SearchResponse;
import app.service.CalcService;
import app.utils.ConvertLocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            CalcResponse calcResponse = calcService.calculate(request, name);
            return ResponseEntity.ok(calcResponse);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchResponse>> findRequests(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime startDate,
                                                             @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime endDate,
                                                             @RequestParam(name = "statement", required = false) String statement,
                                                             @RequestParam(name = "userName", required = false) String userName) {
        SearchRequest request = new SearchRequest();
        request.setStartDate(ConvertLocalDateTime.convertStartDate(startDate));
        request.setEndDate(ConvertLocalDateTime.convertEndDate(endDate));
        request.setStatement(statement);
        request.setUserName(userName);

        List<SearchResponse> result = calcService.findRequests(request);
        return ResponseEntity.ok(result);
    }
}
