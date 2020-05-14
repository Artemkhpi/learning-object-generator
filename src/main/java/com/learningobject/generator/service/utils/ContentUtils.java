package com.learningobject.generator.service.utils;

import com.atlassian.confluence.content.render.xhtml.DefaultConversionContext;
import com.atlassian.confluence.content.render.xhtml.XhtmlException;
import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.confluence.xhtml.api.MacroDefinition;
import com.atlassian.confluence.xhtml.api.XhtmlContent;
import com.learningobject.generator.model.ArticleContentDto;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.atlassian.sal.api.component.ComponentLocator.getComponent;
import static java.util.Arrays.asList;

@Named
public class ContentUtils {
    private static final Logger LOGGER = Logger.getLogger(ContentUtils.class);
    private static final String VLAD_MACRO_NAME = "Knowledge text mapper macro";
    private static final String SCOPE_PARAMETER = "Scope";
    private static final String DIFFICULTY_PARAMETER = "Difficulty";
    private static final String LABELS_PARAMETER = "Labels";


    public List<ArticleContentDto> getLearningTextMacrosBodies(ContentEntityObject contentEntity) {
        List<MacroDefinition> macroDefinitionList = new ArrayList<>();
        getMacros(contentEntity, macroDefinitionList);

        return macroDefinitionList.stream().map(macro -> new ArticleContentDto(macro.getBody().getBody(),
                macro.getParameter(SCOPE_PARAMETER),
                macro.getParameter(DIFFICULTY_PARAMETER),
                asList(macro.getParameter(LABELS_PARAMETER).split("; "))))
                .collect(Collectors.toList());
    }

    private void getMacros(ContentEntityObject contentEntity, List<MacroDefinition> macroDefinitionList) {
        try {
            getComponent(XhtmlContent.class)
                    .handleMacroDefinitions(contentEntity.getBodyAsString(),
                            new DefaultConversionContext(contentEntity.toPageContext()), macroDefinition -> {
                                if (macroDefinition.getName().equals(VLAD_MACRO_NAME)) {
                                    macroDefinitionList.add(macroDefinition);
                                }
                            });
        } catch (XhtmlException e) {
            LOGGER.error("Failed to get macro definition", e);
        }
    }
}
