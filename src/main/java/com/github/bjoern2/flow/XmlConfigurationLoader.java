package com.github.bjoern2.flow;

import com.github.bjoern2.flow.tasklet.Tasklet;
import com.github.bjoern2.flow.xml.Inject;
import com.github.bjoern2.flow.xml.Job;
import com.github.bjoern2.flow.xml.Next;
import com.github.bjoern2.flow.xml.Property;

public class XmlConfigurationLoader {

	public com.github.bjoern2.flow.Job load(Job xmlJob) {
		com.github.bjoern2.flow.Job job = new JobImpl();
		for (Property prop : xmlJob.getProperties()) {
			job.setProperty(prop.getName(), prop.getValue());
		}
		for (com.github.bjoern2.flow.xml.Task xmlTask : xmlJob.getTasks()) {
			com.github.bjoern2.flow.Task<Tasklet> task = new TaskImpl<Tasklet>();
			task.setId(xmlTask.getId());
			try {
				task.setTasklet((Tasklet)Class.forName(xmlTask.getClazz()).newInstance());
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			GenericInjector injector = new GenericInjector();
			for (Inject xmlInject : xmlTask.getInjects()) {
				injector.addMapping(xmlInject.getPropertyName(), xmlInject.getFieldName());
			}
			task.setInjector(injector);
			
			for (Next next : xmlTask.getNexts()) {
				task.addNext(next.getOn(), next.getRef());
			}
			
			
			job.addTask(task);
		}
		
		return job;
	}
	
}
