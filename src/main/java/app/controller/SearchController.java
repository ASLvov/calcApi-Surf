package app.controller;

import app.domain.SearchRequest;
import app.domain.SearchResponse;
import app.domain.UserRequest;
import app.repository.SearchRepository;
import app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/search")
public class SearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    private SearchRepository searchRepository;
    private UserRepository userRepository;

    public SearchController(SearchRepository searchRepository,
                            UserRepository userRepository) {

        this.searchRepository = searchRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/by_date")
    public ResponseEntity<List> findByDate(@RequestBody SearchRequest request) {
        try {
            List<UserRequest> list = searchRepository.findByDate(request.getStartDate(), request.getEndDate());
            List<SearchResponse> responseList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                responseList.add(new SearchResponse(list.get(i).getId(),
                        list.get(i).getDate(),
                        list.get(i).getStatement(),
                        list.get(i).getResult(),
                        list.get(i).getUserId(),
                        userRepository.getOne(list.get(i).getUserId()).getUserName()));
            }
            LOGGER.info("{} records received", responseList.size());
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (NullPointerException exception) {
            LOGGER.info("No such requests");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/by_statement")
    public List<SearchResponse> findByStatement(@RequestBody SearchRequest request) {
        List<UserRequest> list = searchRepository.findByStatement(request.getStatement());
        List<SearchResponse> responseList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            responseList.add(new SearchResponse(list.get(i).getId(),
                    list.get(i).getDate(),
                    list.get(i).getStatement(),
                    list.get(i).getResult(),
                    list.get(i).getUserId(),
                    userRepository.getOne(list.get(i).getUserId()).getUserName()));
        }
        return responseList;
    }
}
