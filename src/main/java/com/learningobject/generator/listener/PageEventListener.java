package com.learningobject.generator.listener;

import com.atlassian.confluence.event.events.content.page.PageCreateEvent;
import com.atlassian.confluence.event.events.content.page.PageCreateFromTemplateEvent;
import com.atlassian.confluence.event.events.content.page.PageUpdateEvent;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PageEventListener extends AbstractEventListener {

    @Inject
    public PageEventListener(@ComponentImport EventPublisher eventPublisher) {
        super(eventPublisher);
    }

    @EventListener
    public void onPageCreatedEvent(PageCreateEvent event) {
        publishAsyncEntityEvent(event.getPage(), "page created");
    }

    @EventListener
    public void onPageCreateFromTemplateEvent(PageCreateFromTemplateEvent event) {
        publishAsyncEntityEvent(event.getPage(), "page created from template");
    }

    @EventListener
    public void onPageUpdateEvent(PageUpdateEvent event) {
        publishAsyncEntityEvent(event.getPage(), "page updated");
    }

}
