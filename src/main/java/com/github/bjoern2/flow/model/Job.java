package com.github.bjoern2.flow.model;

import java.util.List;
import java.util.Properties;

public interface Job {

	void addTask(Task<?> task);

	List<Task<?>> getTasks();

	void setProperty(String key, Object value);
	
	Properties getProperties();

}