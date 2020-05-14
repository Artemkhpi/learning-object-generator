package com.learningobject.generator.service.model;

import net.java.ao.Entity;
import net.java.ao.Preload;
import net.java.ao.schema.Table;

@Preload
@Table("keyword")
public interface KeyWordAO extends Entity {

    ArticleContentAO getArticleAO();
    void setArticleAO(ArticleContentAO articleAO);
    String getKeyWord();
    void setKeyWord(String keyWord);
}
