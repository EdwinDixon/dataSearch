package com.hackathon.stackoverflowDataSearch.elasticsearch;

import com.hackathon.stackoverflowDataSearch.exceptions.DataSearchException;
import com.hackathon.stackoverflowDataSearch.models.ElasticsearchRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class ElasticsearchService {
    private ElasticsearchClient elasticSearchClient;

    public ElasticsearchService() throws DataSearchException {
        this.elasticSearchClient = ElasticsearchClient.getInstance();
    }

    public SearchHits search(ElasticsearchRequest elasticsearchRequest) {
            SearchResponse searchResponse;
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
                searchHits = searchResponse.getHits();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return searchHits;
    }
}
