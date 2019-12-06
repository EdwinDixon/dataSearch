package com.hackathon.stackoverflowDataSearch.elasticsearch;

import com.hackathon.stackoverflowDataSearch.constants.Constants;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticsearchQueryBuilder {
    public QueryBuilder getMatchAllQuery(){
        return QueryBuilders.matchAllQuery();
    }

    public QueryBuilder getTextMatchQuery(String searchText){
        return QueryBuilders.matchQuery(Constants.POST_BODY_FIELD,searchText);
    }
}
