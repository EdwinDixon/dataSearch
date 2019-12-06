package com.hackathon.stackoverflowDataSearch.elasticsearch;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticsearchQueryBuilder {
    public QueryBuilder getMatchAllQuery(){
        return QueryBuilders.matchAllQuery();
    }
}
