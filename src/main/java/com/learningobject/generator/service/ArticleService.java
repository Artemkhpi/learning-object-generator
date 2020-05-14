package com.learningobject.generator.service;

import com.learningobject.generator.service.model.ArticleEntity;

import java.util.List;

public interface ArticleService {
    void saveArticleToDb(ArticleEntity entity);

    ArticleEntity getArticleById(int articleId);
    List<ArticleEntity> getAllArticles();
}
