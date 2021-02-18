package app.service;

import app.controller.dto.CalcRequest;
import app.controller.dto.CalcResponse;
import app.controller.dto.SearchRequest;
import app.controller.dto.SearchResponse;
import app.domain.UserRequests;
import app.repository.UserRepository;
import app.repository.UserRequestRepository;
import app.utils.ReversePolishNotation;
import lombok.AllArgsConstructor;
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

        UserRequests userRequests = new UserRequests();
        userRequests.setStatement(request.getStatement());
        userRequests.setResult(calcResponse.getResult());
        userRequests.setDate(LocalDateTime.now());
        userRequests.setUserId(1L);

        userRequestRepository.save(userRequests);

        return calcResponse;
    }

    public List<SearchResponse> findRequests(SearchRequest request) {
        Long id = userRepository.findByUserName(request.getUserName()).getId();
        List<UserRequests> list = userRequestRepository.findRequests(id,
                request.getStatement(), request.getStartDate(), request.getEndDate());
        return list.stream().map(l -> getResponse(l)).collect(Collectors.toList());
    }

    private SearchResponse getResponse(UserRequests l) {
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