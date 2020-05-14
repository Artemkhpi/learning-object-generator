package com.learningobject.generator.listener;

import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.learningobject.generator.listener.model.AsyncContentEntityEvent;
import com.learningobject.generator.model.ArticleContentDto;
import com.learningobject.generator.service.ArticleService;
import com.learningobject.generator.service.model.ArticleEntity;
import com.learningobject.generator.service.utils.ContentUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class AsyncListener extends AbstractEventListener {

    private ArticleService articleService;
    private final ContentUtils contentUtils;

    @Inject
    AsyncListener(@ComponentImport EventPublisher eventPublisher, ArticleService articleService, ContentUtils contentUtils) {
        super(eventPublisher);
        this.articleService = articleService;
        this.contentUtils = contentUtils;
    }

    @EventListener
    public void onAsyncPageEvent(AsyncContentEntityEvent event) {
        processAsyncContentEntity(event.getContentEntityObject());
    }

    private void processAsyncContentEntity(ContentEntityObject contentEntityObject) {
        List<ArticleContentDto> contentParts = contentUtils.getLearningTextMacrosBodies(contentEntityObject);
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setContent(contentParts);
        articleEntity.setTitle(contentEntityObject.getTitle());
        articleEntity.setScope(contentParts.get(0).getScope());
        articleEntity.setComplexity(contentParts.get(0).getComplexity());
        articleService.saveArticleToDb(articleEntity);
        LOGGER.debug("Found event with content name: " + contentEntityObject.getNameForComparison());
    }
}
