package com.github.bjoern2.flow;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JobImpl implements Job {

	private Properties properties = new Properties();
	private Map<String, Task<?>> tasks = new HashMap<String, Task<?>>();
	
	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Job#start(java.lang.String)
	 */
	@Override
	public void start(String id) {
		Task<?> task = tasks.get(id);
		if (task == null) {
			throw new RuntimeException("Task with id \"" + id + "\" not found.");
		}
		String next = task.start(properties);
		if (next == null || next.isEmpty()) {
			return;
		}
		start(next);
	}
	
	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Job#addTask(com.github.bjoern2.flow.TaskImpl)
	 */
	@Override
	public void addTask(Task<?> task) {
		tasks.put(task.getId(), task);
	}
	
	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Job#clearTasks()
	 */
	@Override
	public void clearTasks() {
		tasks.clear();
	}

	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Job#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}

	@Override
	public void addProperties(Properties properties) {
		this.properties.putAll(properties);
	}
	
}
