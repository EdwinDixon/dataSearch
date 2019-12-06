package com.hackathon.stackoverflowDataSearch.constants;

public class Constants {
    public static final String ELASTICSEARCH_HOST_NAME = "esHostName";
    public static final String ELASTICSEARCH_PROTOCOL = "esProtocol";
    public static final String ELASTICSEARCH_PORT = "esPort";
    public static final String CONFIG_FILE = "config.properties";
    public static final String MAX_RETRY_TIMEOUT = "esRetryTimeOut";
    public static final String GET_QUESTIONS = "/questions";
    public static final String FROM = "from";
    public static final String SIZE = "size";
    public static final String TEXT = "text";
    public static final String INDEX_NAME = "stackoverflow";
    public static final String DOCUMENT_TYPE = "logs";
    public static final String POST_BODY_FIELD = "doc.Body";

    public static final String INTEGER_PARSING_EXCEPTION = "Not able to parse integer from configuration file";

}
