package com.hackathon.stackoverflowDataSearch.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Response {
    @JsonProperty("total")
    private long total;
    @JsonProperty("data")
    private List<Map<String,Object>> data = new ArrayList<>();

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public void addData(Map<String,Object> hitDetails){
        this.data.add(hitDetails);
    }
}
