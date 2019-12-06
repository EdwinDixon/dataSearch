package com.hackathon.stackoverflowDataSearch.controller;

import com.hackathon.stackoverflowDataSearch.constants.Constants;
import com.hackathon.stackoverflowDataSearch.exceptions.DataSearchException;
import com.hackathon.stackoverflowDataSearch.models.Request;
import com.hackathon.stackoverflowDataSearch.services.QuestionControllerService;
import org.elasticsearch.search.SearchHits;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class QuestionsController {

    @RequestMapping(value = Constants.GET_QUESTIONS, method = RequestMethod.GET)
    @ResponseBody
    public SearchHits getQuestions(@RequestParam(Constants.FROM) int from,
                                   @RequestParam(Constants.SIZE) int size) {
        try {
            QuestionControllerService questionControllerService = new QuestionControllerService();
            return questionControllerService.listQuestions(from, size);
        } catch(DataSearchException dataSearchException){
            System.out.println(dataSearchException);
        }
        return null;
    }
}
