package com.github.bjoern2.flow;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.github.bjoern2.flow.xml.Job;
import com.github.bjoern2.flow.xml.XMLReader;

public class XmlConfigurationLoaderTest {

	@Test
	public void test() throws Throwable {
		InputStream in = getClass().getResourceAsStream("xml/helloworld.xml");
		String xml = IOUtils.toString(in);
		
		XMLReader reader = new XMLReader();
		Job job = reader.read(xml);
		
		XmlConfigurationLoader loader = new XmlConfigurationLoader();
		com.github.bjoern2.flow.Job j = loader.load(job);
	}

}
