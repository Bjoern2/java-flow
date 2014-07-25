package com.github.bjoern2.flow;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.github.bjoern2.flow.tasklet.GreetingsTasklet;
import com.github.bjoern2.flow.tasklet.Tasklet;

public class JobImplTest {

	private Job job;
	
	@Before
	public void setUp() throws Exception {
		job = new JobImpl();
		job.setProperty("message", "Hello GitHub!");
		
		
		TaskImpl<GreetingsTasklet> task1 = new TaskImpl<GreetingsTasklet>();
		task1.setId("");
		task1.setTasklet(new GreetingsTasklet());
		task1.setInjector(new PropertyInjector<GreetingsTasklet>() {
			@Override
			public void inject(GreetingsTasklet tasklet, Properties properties) {
				tasklet.setMessage(properties.getProperty("message"));
				
			}
		});
		task1.addNext("SUCCESS", "step2");
		job.addTask(task1);
		
		TaskImpl<GreetingsTasklet> task2 = new TaskImpl<GreetingsTasklet>();
		task2.setId("step2");
		task2.setTasklet(new GreetingsTasklet());
		task2.setInjector(new PropertyInjector<GreetingsTasklet>() {
			@Override
			public void inject(GreetingsTasklet tasklet, Properties properties) {
				tasklet.setMessage("Hello Björn");
				
			}
		});
		job.addTask(task2);
		
		
	}

	@Test
	public void testStart() {
		job.start("");
	}

}
