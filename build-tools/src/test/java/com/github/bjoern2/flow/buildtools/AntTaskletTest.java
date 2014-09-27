package com.github.bjoern2.flow.buildtools;

import java.util.Arrays;

import org.junit.Test;

public class AntTaskletTest {

	@Test
	public void test() throws Throwable {
		AntTasklet t = new AntTasklet();
//		t.setDirectory(directory);
		t.setTasks(Arrays.asList("-version"));
		t.setAntHome("C:\\develop\\ant\\apache-ant-1.9.4");
		t.execute();
	}

}
