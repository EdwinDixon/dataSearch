package com.hackathon.stackoverflowDataSearch.elasticsearch;

import com.hackathon.stackoverflowDataSearch.exceptions.DataSearchException;
import com.hackathon.stackoverflowDataSearch.models.ElasticsearchRequest;
import com.hackathon.stackoverflowDataSearch.models.Response;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class ElasticsearchService {
    private ElasticsearchClient elasticSearchClient;

    public ElasticsearchService() throws DataSearchException {
        this.elasticSearchClient = ElasticsearchClient.getInstance();
    }

    public Response search(ElasticsearchRequest elasticsearchRequest) {
            SearchResponse searchResponse = null;
            SearchHits searchHits = null;
            SearchRequest searchRequest = new SearchRequest(elasticsearchRequest.getIndexName());
            searchRequest.types(elasticsearchRequest.getDocumentType());
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(elasticsearchRequest.getQuery());
            searchSourceBuilder.size(elasticsearchRequest.getSize());
            searchSourceBuilder.from(elasticsearchRequest.getFrom());
            searchRequest.source(searchSourceBuilder);
            try {
                searchResponse = this.elasticSearchClient.search(searchRequest);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return convertHitsToSearchResponse(searchResponse.getHits());
    }

    private Response convertHitsToSearchResponse(SearchHits searchHits){
        Response response = new Response();
        response.setTotal(searchHits.getTotalHits());
        Arrays.stream(searchHits.getHits())
                .map(hit -> hit.getSourceAsMap())
                .forEach(response::addData);
        return response;
    }
}
