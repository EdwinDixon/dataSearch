package com.hackathon.stackoverflowDataSearch.services;

import com.hackathon.stackoverflowDataSearch.constants.Constants;
import com.hackathon.stackoverflowDataSearch.elasticsearch.ElasticsearchQueryBuilder;
import com.hackathon.stackoverflowDataSearch.elasticsearch.ElasticsearchService;
import com.hackathon.stackoverflowDataSearch.exceptions.DataSearchException;
import com.hackathon.stackoverflowDataSearch.models.ElasticsearchRequest;
import com.hackathon.stackoverflowDataSearch.models.Request;
import com.hackathon.stackoverflowDataSearch.models.Response;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.SearchHits;

public class QuestionControllerService {
    private ElasticsearchService elasticsearchService;

    public QuestionControllerService() throws DataSearchException {
        this.elasticsearchService = new ElasticsearchService();
    }

    public Response listQuestions(int from, int size,String searchText){
        ElasticsearchQueryBuilder elasticsearchQueryBuilder = new ElasticsearchQueryBuilder();
        ElasticsearchRequest elasticsearchRequest = new ElasticsearchRequest();
        elasticsearchRequest.setDocumentType(Constants.DOCUMENT_TYPE);
        elasticsearchRequest.setIndexName(Constants.INDEX_NAME);
        elasticsearchRequest.setFrom(from);
        elasticsearchRequest.setSize(size);
        if(searchText==null) {
            elasticsearchRequest.setQuery(elasticsearchQueryBuilder.getMatchAllQuery());
        } else {
            elasticsearchRequest.setQuery(elasticsearchQueryBuilder.getTextMatchQuery(searchText));
        }
        return this.elasticsearchService.search(elasticsearchRequest);
    }

}
