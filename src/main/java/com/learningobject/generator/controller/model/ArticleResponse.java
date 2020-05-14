package com.learningobject.generator.controller.model;

import com.google.gson.annotations.SerializedName;
import com.learningobject.generator.model.ArticleContentDto;

import java.util.List;

public final class ArticleResponse {
    @SerializedName("article_id")
    private long articleId;
    private String title;
    private List<String> keywords;
    private String scope;
    private String complexity;
    private List<ArticleContentDto> content;

    public ArticleResponse() {
    }

    public ArticleResponse(long articleId, String title, List<String> keywords,
                           String scope, String complexity, List<ArticleContentDto> content) {
        this.articleId = articleId;
        this.title = title;
        this.keywords = keywords;
        this.scope = scope;
        this.complexity = complexity;
        this.content = content;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public List<ArticleContentDto> getContent() {
        return content;
    }

    public void setContent(List<ArticleContentDto> content) {
        this.content = content;
    }
}
