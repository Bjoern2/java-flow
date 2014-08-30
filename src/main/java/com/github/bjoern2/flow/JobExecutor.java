package com.github.bjoern2.flow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.github.bjoern2.flow.tasklet.Tasklet;
import com.github.bjoern2.flow.xml.Inject;
import com.github.bjoern2.flow.xml.Job;
import com.github.bjoern2.flow.xml.Next;
import com.github.bjoern2.flow.xml.Task;

/**
 * Created by bjoern on 09.06.2014.
 */
@Deprecated
public class JobExecutor {

    private Job jobDefinition;

    private Map<String, Object> properties = new HashMap<>();

    public JobExecutor(Job jobDefinition) {
        this.jobDefinition = jobDefinition;
        
//        for (Property prop : jobDefinition.getProperties()) {
//        	properties.put(prop.getName(), prop.getValue());
//        }
    }

    public void start() throws Throwable {
        Task task = jobDefinition.getTasks().get(0);
        start(task);
    }

    public void start(String taskId) throws Throwable {
        Task task = findTask(taskId);
        start(task);
    }

    protected void start(Task task) throws Throwable {
        String clazz = task.getClazz();
        Class<?> c = Class.forName(clazz);
        Tasklet t = (Tasklet)c.newInstance();

        inject(t, task);

        String status;
        try {
            status = t.execute();
        } catch (Throwable e) {
            status = "EXCEPTION";
            properties.put("exception", e);
        }
        
        

        for (Next next : task.getNexts()) {
            if (status.equals(next.getOn())) {
                Task nextTask = findTask(next.getRef());
                start(nextTask);
            }
        }
    }

    private void inject(Tasklet t, Task task) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    	Injectable i = null;
    	if (t.getClass().isAssignableFrom(Injectable.class)) {
    		i = (Injectable)t;
    	}
    	
    	
//    	Method method = null;
//    	try {
//    		method = t.getClass().getMethod("setProperty", String.class, Object.class);
//    	} catch (NoSuchMethodException e) {
//    		method = null;
//    	}
        for (Inject inject : task.getInjects()) {
        	Object value = properties.get(inject.getPropertyRef());
        	
            try {
                PropertyUtils.setProperty(t, inject.getName(), value);
            } catch (Exception e) {
            	if (i != null) {
            		i.inject(inject.getName(), value);
            	} else {
            		throw new RuntimeException("Can not inject property " + inject.getName());
            	}
            }
        }
    }
    
    private void eject() {
    	
    }


    private Task findTask(String id) {
        for (Task task : jobDefinition.getTasks()) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

}
