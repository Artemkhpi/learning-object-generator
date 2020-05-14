package com.learningobject.generator.impl;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;
import com.learningobject.generator.api.LearningObjectGenerator;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService ({LearningObjectGenerator.class})
@Named ("myPluginComponent")
public class LearningObjectGeneratorPluginImpl implements LearningObjectGenerator
{
    @ComponentImport
    private final ApplicationProperties applicationProperties;

    @Inject
    public LearningObjectGeneratorPluginImpl(final ApplicationProperties applicationProperties)
    {
        this.applicationProperties = applicationProperties;
    }

    public String getName()
    {
        if(null != applicationProperties)
        {
            return "myComponent:" + applicationProperties.getDisplayName();
        }
        
        return "myComponent";
    }
}