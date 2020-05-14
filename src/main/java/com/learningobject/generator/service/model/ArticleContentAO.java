package com.learningobject.generator.service.model;

import net.java.ao.Entity;
import net.java.ao.OneToMany;
import net.java.ao.Preload;
import net.java.ao.schema.AutoIncrement;
import net.java.ao.schema.Table;

@Preload
@Table("article_content")
public interface ArticleContentAO extends Entity {

    @OneToMany
    KeyWordAO[] getKeyWordAO();
    @OneToMany
    void setKeyWordAO(KeyWordAO[] keywords);

    ArticleAO getArticleAO();
    void setArticleAO(ArticleAO articleAO);

    String getHtmlBody();

    void setHtmlBody(String htmlBody);

    String getScope();
    void setScope(String scope);

    String getComplexity();
    void setComplexity(String complexity);
}
