package com.learningobject.generator.controller.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ArticlesResponse {
    private List<LightArticleResponse> articles;
    @SerializedName("total_pages")
    private int totalPages;
    private int currentPage;

    public ArticlesResponse() {
    }

    public ArticlesResponse(List<LightArticleResponse> articles, int totalPages, int currentPage) {
        this.articles = articles;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    public List<LightArticleResponse> getArticles() {
        return articles;
    }

    public void setArticles(List<LightArticleResponse> articles) {
        this.articles = articles;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
