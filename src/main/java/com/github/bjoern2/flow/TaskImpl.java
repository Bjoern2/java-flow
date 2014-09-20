package com.github.bjoern2.flow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.bjoern2.flow.tasklet.Tasklet;

public class TaskImpl<T extends Tasklet> implements Task<T> {

	private String id;
	private T tasklet;
	private PropertyInjector<T> injector;
	private PropertyEjector<T> ejector;
	
	private Map<String, String> nexts = new HashMap<String, String>();

	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Task#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Task#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	public T getTasklet() {
		return tasklet;
	}

	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Task#setTasklet(T)
	 */
	@Override
	public void setTasklet(T tasklet) {
		this.tasklet = tasklet;
	}

	public PropertyInjector<T> getInjector() {
		return injector;
	}

	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Task#setInjector(com.github.bjoern2.flow.PropertyInjector)
	 */
	@Override
	public void setInjector(PropertyInjector<T> injector) {
		this.injector = injector;
	}

	public PropertyEjector<T> getEjector() {
		return ejector;
	}

	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Task#setEjector(com.github.bjoern2.flow.PropertyEjector)
	 */
	@Override
	public void setEjector(PropertyEjector<T> ejector) {
		this.ejector = ejector;
	}
	
	/* (non-Javadoc)
	 * @see com.github.bjoern2.flow.Task#addNext(java.lang.String, java.lang.String)
	 */
	@Override
	public void addNext(String on, String taskId) {
		nexts.put(on, taskId);
	}

    @Override
    public List<String> getNexts(String result) {
        return Arrays.asList(nexts.get(result));
    }
	
}
