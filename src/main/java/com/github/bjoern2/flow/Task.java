package com.github.bjoern2.flow;

import java.util.List;

import com.github.bjoern2.flow.tasklet.Tasklet;

public interface Task<T extends Tasklet> {

	String getId();
	
	void setId(String id);

	void setTasklet(T tasklet);

	void setInjector(PropertyInjector<T> injector);
	
	PropertyInjector<T> getInjector();

	void setEjector(PropertyEjector<T> ejector);
	
	PropertyEjector<T> getEjector();

	void addNext(String on, String taskId);
	
	List<String> getNexts(String result);

    T getTasklet();

}