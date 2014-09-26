package com.github.bjoern2.flow.buildtools;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

public class MavenTaskletTest {

	@Ignore
	@Test
	public void test() throws Throwable {
		MavenTasklet t = new MavenTasklet();
		t.setMavenHome("C:\\develop\\maven\\apache-maven-3.2.3");
		t.setJavaHome("C:\\Program Files\\Java\\jdk1.7.0_45");
		t.setGoals(Arrays.asList("-version"));
		t.execute();
	}
	

}
