package com.github.bjoern2.flow.loader;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import com.github.bjoern2.flow.xml.Job;
import com.github.bjoern2.flow.xml.XMLReader;

public class XmlConfigurationLoaderTest {

    @Test
    public void testLoadString() throws Throwable {
        InputStream in = getClass().getResourceAsStream("../xml/helloworld.xml");
        String xml = IOUtils.toString(in);

        XMLReader reader = new XMLReader();
        Job job = reader.read(xml);

        XmlConfigurationLoader loader = new XmlConfigurationLoader();
        com.github.bjoern2.flow.model.Job j = loader.load(job);
        Assert.assertNotNull(j);
    }

    @Test
    public void testLoadStream() throws Throwable {
        InputStream in = getClass().getResourceAsStream("../xml/helloworld.xml");
        XmlConfigurationLoader loader = new XmlConfigurationLoader();
        com.github.bjoern2.flow.model.Job j = loader.load(in);
        Assert.assertNotNull(j);
    }

}
