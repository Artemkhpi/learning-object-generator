package com.learningobject.generator.listener;

import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.learningobject.generator.listener.model.AsyncContentEntityEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public abstract class AbstractEventListener implements DisposableBean, InitializingBean {
    static final Logger LOGGER = Logger.getLogger(AbstractEventListener.class);

    @ComponentImport
    private EventPublisher eventPublisher;

    @Inject
    AbstractEventListener(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void destroy() {
        eventPublisher.unregister(this);
    }

    /**
     * Called when the plugin has been enabled.
     */
    @Override
    public void afterPropertiesSet() {
        eventPublisher.register(this);
    }

    void publishAsyncEntityEvent(ContentEntityObject content, String eventName) {
        LOGGER.debug("Before publishing event : " + eventName);
        eventPublisher.publish(new AsyncContentEntityEvent(content));
        LOGGER.debug("After publishing event: " + eventName);
    }


}
