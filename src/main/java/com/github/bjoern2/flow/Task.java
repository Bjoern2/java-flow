package com.github.bjoern2.flow;

import java.util.Properties;

import com.github.bjoern2.flow.tasklet.Tasklet;

public interface Task<T extends Tasklet> {

	String start(Properties properties);

	String getId();
	
	void setId(String id);

	void setTasklet(T tasklet);

	void setInjector(PropertyInjector<T> injector);

	void setEjector(PropertyEjector<T> ejector);

	void addNext(String on, String taskId);

}