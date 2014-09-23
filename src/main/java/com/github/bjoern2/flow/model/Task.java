package com.github.bjoern2.flow.model;

import java.util.List;

public interface Task<T extends Tasklet> {

	String getId();
	
	void setId(String id);

	void setTasklet(T tasklet);

	void setInjector(PropertyInjector<T> injector);
	
	PropertyInjector<T> getInjector();

	void setEjector(PropertyEjector<T> ejector);
	
	PropertyEjector<T> getEjector();


	List<Next> getNexts();
	
    T getTasklet();

}