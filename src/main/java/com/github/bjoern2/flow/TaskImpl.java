package com.github.bjoern2.flow;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.github.bjoern2.flow.tasklet.Tasklet;

public class TaskImpl<T extends Tasklet> {

	private String id;
	private T tasklet;
	private PropertyInjector<T> injector;
	private PropertyEjector<T> ejector;
	
	private Map<String, String> nexts = new HashMap<String, String>();
	
	public String start(Properties properties) {
		if (injector != null) {
			injector.inject(tasklet, properties);
		}
		String result;
		try {
			result = tasklet.execute();
		} catch (Throwable e) {
			result = "EXCEPTION";
			properties.put("EXCEPTION", e);
		}
		if (ejector != null) {
			ejector.eject(tasklet, properties);
		}
//		nexts.get(result).run();
		return nexts.get(result);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Tasklet getTasklet() {
		return tasklet;
	}

	public void setTasklet(T tasklet) {
		this.tasklet = tasklet;
	}

	public PropertyInjector<T> getInjector() {
		return injector;
	}

	public void setInjector(PropertyInjector<T> injector) {
		this.injector = injector;
	}

	public PropertyEjector<?> getEjector() {
		return ejector;
	}

	public void setEjector(PropertyEjector<T> ejector) {
		this.ejector = ejector;
	}

	public Map<String, String> getNexts() {
		return nexts;
	}

	public void setNexts(Map<String, String> nexts) {
		this.nexts = nexts;
	}
	
	public void addNext(String on, String taskId) {
		nexts.put(on, taskId);
	}
	
}
