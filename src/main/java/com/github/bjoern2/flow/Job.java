package com.github.bjoern2.flow;

import java.util.Properties;

public interface Job {

	void start(String id);

	void addTask(Task<?> task);

	void clearTasks();

	void setProperty(String key, Object value);
	
	void addProperties(Properties properties);

}