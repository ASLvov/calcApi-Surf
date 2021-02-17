package app.service;

import app.domain.*;
import app.repository.UserRepository;
import app.repository.UserRequestRepository;
import app.utils.ReversePolishNotation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CalcService {

    private final UserRequestRepository userRequestRepository;
    private final UserRepository userRepository;

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
        List<UserRequest> list = userRequestRepository.findRequests(userRepository.getUser(request.getUserName()).getId(),
                request.getStatement(), request.getStartDate(), request.getEndDate());
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