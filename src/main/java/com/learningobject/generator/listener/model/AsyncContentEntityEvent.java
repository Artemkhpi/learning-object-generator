package com.learningobject.generator.listener.model;

import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.event.api.AsynchronousPreferred;

@AsynchronousPreferred
public class AsyncContentEntityEvent {
    private ContentEntityObject contentEntityObject;

    public AsyncContentEntityEvent(ContentEntityObject contentEntityObject) {
        this.contentEntityObject = contentEntityObject;
    }

    public AsyncContentEntityEvent() {
    }

    public ContentEntityObject getContentEntityObject() {
        return contentEntityObject;
    }

    public void setContentEntityObject(ContentEntityObject contentEntityObject) {
        this.contentEntityObject = contentEntityObject;
    }
}
