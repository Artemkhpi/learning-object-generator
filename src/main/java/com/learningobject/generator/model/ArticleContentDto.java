package com.learningobject.generator.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleContentDto {
    @SerializedName("part_id")
    private int partId;
    @SerializedName("html_body")
    private String htmlBody;
    private String scope;
    private String complexity;
    private List<String> keywords;

    public ArticleContentDto(int partId, String htmlBody, String scope, String complexity, List<String> keywords) {
        this.partId = partId;
        this.htmlBody = htmlBody;
        this.scope = scope;
        this.complexity = complexity;
        this.keywords = keywords;
    }

    public ArticleContentDto(String htmlBody, String scope, String complexity, List<String> keywords) {
        this.htmlBody = htmlBody;
        this.scope = scope;
        this.complexity = complexity;
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

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public ArticleContentDto() {
    }

    public ArticleContentDto(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public ArticleContentDto(int partId, String htmlBody) {
        this.partId = partId;
        this.htmlBody = htmlBody;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }
}
