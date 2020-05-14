package it.com.learningobject.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.learningobject.generator.api.LearningObjectGenerator;
import com.atlassian.sal.api.ApplicationProperties;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class MyComponentWiredTest
{
    private final ApplicationProperties applicationProperties;
    private final LearningObjectGenerator learningObjectGenerator;

    public MyComponentWiredTest(ApplicationProperties applicationProperties, LearningObjectGenerator learningObjectGenerator)
    {
        this.applicationProperties = applicationProperties;
        this.learningObjectGenerator = learningObjectGenerator;
    }

    @Test
    public void testMyName()
    {
        assertEquals("names do not match!", "myComponent:" + applicationProperties.getDisplayName(), learningObjectGenerator.getName());
    }
}