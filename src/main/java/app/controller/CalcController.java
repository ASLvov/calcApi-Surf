package app.controller;

import app.domain.CalcRequest;
import app.domain.CalcResponse;
import app.domain.User;
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

    private CalcService calcService;

    private UserRepository userRepository;

    private UserRequestRepository userRequestRepository;

    public CalcController(CalcService calcService, UserRepository userRepository, UserRequestRepository userRequestRepository) {
        this.calcService = calcService;
        this.userRepository = userRepository;
        this.userRequestRepository = userRequestRepository;
    }

    @PostMapping
    public ResponseEntity<CalcResponse> calculate(@RequestBody CalcRequest request) {
        CalcResponse calcResponse = calcService.calculate(request);
        User user = new User();
        user.setUserName("Al");
        user.setPassword("123");
        userRepository.save(user);
        UserRequest userRequest = new UserRequest();
        userRequest.setStatement(request.getStatement());
        userRequest.setResult(calcResponse.getResult());
        userRequest.setDate(LocalDateTime.now());
        userRequest.setUserId(user.getId());
        userRequestRepository.save(userRequest);
        return new ResponseEntity<>(calcResponse, HttpStatus.OK);
    }
}
