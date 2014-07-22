package com.github.bjoern2.flow;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JobImpl {

	private Properties properties = new Properties();
	private Map<String, TaskImpl> tasks = new HashMap<String, TaskImpl>();
	
	public void start(String id) {
		TaskImpl task = tasks.get(id);
		String next = task.start(properties);
		start(next);
	}
	
	public void addTask(TaskImpl task) {
		tasks.put(task.getId(), task);
	}
	
	public void clearTasks() {
		tasks.clear();
	}

	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
	
	
	
	
	
}
