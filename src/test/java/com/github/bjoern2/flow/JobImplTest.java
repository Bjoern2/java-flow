package com.github.bjoern2.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;

import com.github.bjoern2.flow.model.Job;
import com.github.bjoern2.flow.model.JobImpl;
import com.github.bjoern2.flow.model.Next;
import com.github.bjoern2.flow.model.NextImpl;
import com.github.bjoern2.flow.model.PropertyInjector;
import com.github.bjoern2.flow.model.TaskImpl;
import com.github.bjoern2.flow.tasklet.GreetingsTasklet;

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
        List<Next> nexts = new ArrayList<>();
        nexts.add(new NextImpl("SUCCESS", "step2"));
        task1.setNexts(nexts);
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

}
