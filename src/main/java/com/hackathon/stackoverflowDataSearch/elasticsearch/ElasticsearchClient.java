package com.hackathon.stackoverflowDataSearch.elasticsearch;

import com.hackathon.stackoverflowDataSearch.configuration.PropertiesLoader;
import com.hackathon.stackoverflowDataSearch.constants.Constants;
import com.hackathon.stackoverflowDataSearch.exceptions.DataSearchException;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ElasticsearchClient {
    private static ElasticsearchClient INSTANCE = null;
    public static RestHighLevelClient client = null;
    private final PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
    private static final Logger logger = LogManager.getLogger(ElasticsearchClient.class);

    private String hostName;
    private int elasticsearchPort;
    private int maxRetryTimeoutMillis;
    private String protocol;

    private ElasticsearchClient() throws DataSearchException {
        System.out.println(propertiesLoader.getValue(Constants.ELASTICSEARCH_HOST_NAME));
        this.hostName = propertiesLoader.getValue(Constants.ELASTICSEARCH_HOST_NAME);
        this.protocol = propertiesLoader.getValue(Constants.ELASTICSEARCH_PROTOCOL);
        try {
            System.out.println(propertiesLoader.getValue(Constants.ELASTICSEARCH_PORT));
            this.elasticsearchPort = Integer.valueOf(propertiesLoader.getValue(Constants.ELASTICSEARCH_PORT));
        } catch(NumberFormatException exception){
            throw new DataSearchException(Constants.INTEGER_PARSING_EXCEPTION,exception);
        }

        try {
            this.maxRetryTimeoutMillis = Integer.valueOf(propertiesLoader.getValue(Constants.MAX_RETRY_TIMEOUT));
        } catch(NumberFormatException exception) {
            throw new DataSearchException(Constants.INTEGER_PARSING_EXCEPTION,exception);
        }



        RestClient restClient = RestClient.builder(new HttpHost(hostName, elasticsearchPort, protocol))
                .setMaxRetryTimeoutMillis(maxRetryTimeoutMillis)
                .build();

        client = new RestHighLevelClient(restClient);
    }

    public static ElasticsearchClient getInstance() throws DataSearchException {
        if (INSTANCE == null) {
            INSTANCE = new ElasticsearchClient();
        }
        return INSTANCE;

    }

    public RestHighLevelClient getClient() {
        return client;
    }

    public SearchResponse search(SearchRequest searchRequest) throws IOException {
        return this.getClient().search(searchRequest);
    }

}
