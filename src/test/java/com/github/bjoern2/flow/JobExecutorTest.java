package com.github.bjoern2.flow;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import com.github.bjoern2.flow.xml.Job;
import com.github.bjoern2.flow.xml.XMLReader;

public class JobExecutorTest {
	
//	private JobExecutor jobExecutor;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testStartString() throws Throwable {
		InputStream in = getClass().getResourceAsStream("xml/helloworld.xml");
		String xml = IOUtils.toString(in);
		
		XMLReader reader = new XMLReader();
		Job job = reader.read(xml);
		
		XmlConfigurationLoader l = new XmlConfigurationLoader();
		com.github.bjoern2.flow.Job j = l.load(job);
		j.start("start");

		
	}

}
