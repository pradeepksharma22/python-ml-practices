package com.github.pradeepksharma22.nyt.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleResponse {
    @SerializedName("results")
    @Expose
    private List<Article> results = null;

    public List<Article> getResults() {
        return results;
    }
}
