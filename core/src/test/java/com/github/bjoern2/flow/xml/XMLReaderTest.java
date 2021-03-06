package com.github.bjoern2.flow.xml;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XMLReaderTest {

	private XMLReader reader;
	
	@Before
	public void setUp() throws Exception {
		reader = new XMLReader();
	}

	@Test
	public void testRead() throws Throwable {
		InputStream in = getClass().getResourceAsStream("audio.xml");
		String xml = IOUtils.toString(in);
		
		Job job = reader.read(xml);
		
		Assert.assertNotNull(job);
	}

}
