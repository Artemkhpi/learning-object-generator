package com.learningobject.generator.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.learningobject.generator.model.ArticleContentDto;
import com.learningobject.generator.service.ArticleService;
import com.learningobject.generator.service.model.ArticleAO;
import com.learningobject.generator.service.model.ArticleContentAO;
import com.learningobject.generator.service.model.ArticleEntity;
import com.learningobject.generator.service.model.KeyWordAO;
import org.springframework.util.StringUtils;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Named
public class ArticleServiceImpl implements ArticleService {
    @ComponentImport
    private final ActiveObjects activeObjects;

    public ArticleServiceImpl(ActiveObjects activeObjects) {
        this.activeObjects = activeObjects;
    }

    @Override
    public void saveArticleToDb(ArticleEntity entity) {
        ArticleAO savedArticle = activeObjects.executeInTransaction(() -> {
            ArticleAO articleAO = activeObjects.create(ArticleAO.class);
            articleAO.setTitle(entity.getTitle());
            articleAO.setScope(entity.getScope());
            articleAO.setComplexity(entity.getComplexity());
            articleAO.save();
            return articleAO;
                }
        );
        activeObjects.executeInTransaction(() -> {
            Stream<ArticleContentAO> savedArticleContents =
                    entity.getContent().stream().map(content -> saveArticleContent(content, savedArticle));
            return savedArticleContents.collect(Collectors.toList());
        });

        activeObjects.executeInTransaction( () -> {
            entity.getContent()
                    .forEach(content -> content.getKeywords().forEach(key -> {
                        KeyWordAO keyWordAO = activeObjects.create(KeyWordAO.class);
                        keyWordAO.setKeyWord(key);
                        keyWordAO.setArticleAO(activeObjects.get(ArticleContentAO.class, content.getPartId()));
                        keyWordAO.save();
                    }));
            return null;
        });
    }

    @Override
    public ArticleEntity getArticleById(int articleId) {
        ArticleAO articleAO = activeObjects.get(ArticleAO.class, articleId);
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(articleAO.getTitle());
        articleEntity.setComplexity(articleAO.getComplexity());
        articleEntity.setContent(Stream.of(articleAO.getContent())
                .map(ao -> new ArticleContentDto(ao.getID(), ao.getHtmlBody(),
                        ao.getScope(), ao.getComplexity(),
                        Stream.of(ao.getKeyWordAO()).map(KeyWordAO::getKeyWord).collect(Collectors.toList())))
                .collect(Collectors.toList()));
        articleEntity.setArticleId(articleAO.getID());
        articleEntity.setKeywords(getKeyWords(articleAO));
        articleEntity.setScope(articleAO.getScope());
        return articleEntity;
    }

    private List<String> getKeyWords(ArticleAO articleAO) {
        return Stream.of(articleAO.getContent())
                .flatMap(content -> Stream.of(content.getKeyWordAO()))
                .map(KeyWordAO::getKeyWord)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleEntity> getAllArticles() {
        return Stream.of(activeObjects.find(ArticleAO.class)).map(articleAO ->  {
            ArticleEntity articleEntity = new ArticleEntity();
            articleEntity.setComplexity(articleAO.getComplexity());
            articleEntity.setScope(articleAO.getScope());
            articleEntity.setTitle(articleAO.getTitle());
            articleEntity.setKeywords(getKeyWords(articleAO));
            articleEntity.setArticleId(articleAO.getID());
            return articleEntity;
        }).collect(Collectors.toList());
    }

    private ArticleContentAO saveArticleContent(ArticleContentDto dto, ArticleAO articleAO) {
        ArticleContentAO articleContentAO = activeObjects.create(ArticleContentAO.class);
        articleContentAO.setHtmlBody(dto.getHtmlBody());
        articleContentAO.setArticleAO(articleAO);
        articleContentAO.setScope(dto.getScope());
        articleContentAO.setComplexity(dto.getComplexity());
        articleContentAO.save();
        dto.setPartId(articleContentAO.getID());
        return articleContentAO;
    }

}