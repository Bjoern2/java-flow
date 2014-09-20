package com.github.bjoern2.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JobImpl implements Job {

	private Properties properties = new Properties();
	//private Map<String, Task<?>> tasks = new HashMap<String, Task<?>>();
	private List<Task<?>> tasks = new ArrayList<Task<?>>();
	
	
	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Job#addTask(com.github.bjoern2.flow.TaskImpl)
	 */
	@Override
	public void addTask(Task<?> task) {
		tasks.add(task);
	}
	
	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Job#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}

    @Override
    public List<Task<?>> getTasks() {
        return tasks;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

	
}
