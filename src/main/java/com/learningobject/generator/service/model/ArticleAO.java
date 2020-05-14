package com.learningobject.generator.service.model;

import net.java.ao.Entity;
import net.java.ao.OneToMany;
import net.java.ao.Preload;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.Table;

@Preload
@Table("article")
public interface ArticleAO extends Entity {

    String getTitle();

    void setTitle(String title);

    String getScope();

    void setScope(String scope);
    String getComplexity();

    void setComplexity(String complexity);
    @OneToMany
    ArticleContentAO[] getContent();
    @OneToMany
    void setContent(ArticleContentAO[] content);
}
