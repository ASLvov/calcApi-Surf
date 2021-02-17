package app.service;

import app.domain.*;
import app.repository.UserRepository;
import app.repository.UserRequestRepository;
import app.utils.ReversePolishNotation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalcService {

    private UserRequestRepository userRequestRepository;
    private UserRepository userRepository;

    public CalcService(UserRequestRepository userRequestRepository,
                       UserRepository userRepository) {
        this.userRequestRepository = userRequestRepository;
        this.userRepository = userRepository;
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

    public List<SearchResponse> findRequests(SearchRequest request) {
        Long userId;
        String statement;
        LocalDateTime startDate;
        LocalDateTime endDate;
        if (!request.getUserName().isEmpty()) {
            userId = userRepository.getUser(request.getUserName()).getId();
        } else {
            userId = 0L;
        }
        if (!request.getStatement().isEmpty()) {
            statement = request.getStatement();
        } else {
            statement = "'%'";
        }
        if (!request.getStartDate().toString().isEmpty()) {
            startDate = request.getStartDate();
        } else {
            startDate = LocalDateTime.MIN;
        }
        if (!request.getEndDate().toString().isEmpty()) {
            endDate = request.getStartDate();
        } else {
            endDate = LocalDateTime.MAX;
        }
        List<UserRequest> list = userRequestRepository.findRequests(userId, statement, startDate, endDate);
        List<SearchResponse> responseList = list.stream().map(l -> getResponse(l)).collect(Collectors.toList());
        return responseList;
    }

    private SearchResponse getResponse(UserRequest l) {
        SearchResponse sr = new SearchResponse();
        sr.setId(l.getId());
        sr.setLocalDateTime(l.getDate());
        sr.setStatement(l.getStatement());
        sr.setResult(l.getResult());
        sr.setUserId(l.getUserId());
        sr.setUserName(userRepository.getOne(l.getUserId()).getUserName());
        return sr;
    }
}
