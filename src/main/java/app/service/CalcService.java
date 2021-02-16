package app.service;

import app.domain.UserRequest;
import app.repository.UserRequestRepository;
import app.utils.ReversePolishNotation;
import app.domain.CalcRequest;
import app.domain.CalcResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class CalcService {

    private UserRequestRepository userRequestRepository;

    public CalcService(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }

    public CalcResponse calculate(CalcRequest request) {
        CalcResponse calcResponse = new CalcResponse(ReversePolishNotation.eval(request.getStatement()).toString());
        UserRequest userRequest = new UserRequest();
        userRequest.setStatement(request.getStatement());
        userRequest.setResult(calcResponse.getResult());
        userRequest.setDate(LocalDateTime.now());
        userRequest.setUserId(1L);
        userRequestRepository.save(userRequest);
        return calcResponse;
    }
}
