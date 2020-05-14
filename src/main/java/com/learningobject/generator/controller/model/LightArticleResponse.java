package com.learningobject.generator.controller.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class LightArticleResponse {
    @SerializedName("article_id")
    private long articleId;
    private String title;
    private List<String> keywords;

    public LightArticleResponse(long articleId, String title, List<String> keywords) {
        this.articleId = articleId;
        this.title = title;
        this.keywords = keywords;
    }

    public LightArticleResponse() {
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
