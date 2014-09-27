package com.github.bjoern2.flow.buildtools;

import java.util.Arrays;

import org.junit.Test;

public class GradleTaskletTest {

	@Test
	public void test() throws Throwable {
		GradleTasklet t = new GradleTasklet();
//		t.setDirectory(directory);
		t.setTasks(Arrays.asList("-version"));
		t.setGradleHome("C:\\develop\\gradle\\gradle-2.1");
		t.execute();
	}

}
