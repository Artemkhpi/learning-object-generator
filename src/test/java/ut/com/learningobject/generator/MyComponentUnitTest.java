package ut.com.learningobject.generator;

import org.junit.Test;
import com.learningobject.generator.api.LearningObjectGenerator;
import com.learningobject.generator.impl.LearningObjectGeneratorPluginImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        LearningObjectGenerator component = new LearningObjectGeneratorPluginImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}