package com.github.bjoern2.flow;

import com.github.bjoern2.flow.tasklet.Tasklet;
import com.github.bjoern2.flow.xml.Inject;
import com.github.bjoern2.flow.xml.Job;
import com.github.bjoern2.flow.xml.Next;
import com.github.bjoern2.flow.xml.Task;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjoern on 09.06.2014.
 */
public class JobExecutor {

    private Job jobDefinition;

    private Map<String, Object> properties = new HashMap<>();

    public JobExecutor(Job jobDefinition) {
        this.jobDefinition = jobDefinition;
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

        injectProperties(t, task);

        String status;
        try {
            status = t.execute();
        } catch (Throwable e) {
            status = "EXCEPTION";
            properties.put("exception", e);
        }

        for (Next next : task.getNexts()) {
            if ("SUCCESS".equals(next.getOn())) {
                Task nextTask = findTask(next.getRef());
                start(nextTask);
            }
        }
    }

    private void injectProperties(Tasklet t, Task task) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final Method method = t.getClass().getMethod("setProperty", String.class, Object.class);
        for (Inject inject : task.getInjects()) {
            try {
                PropertyUtils.setProperty(t, inject.getFieldName(), properties.get(inject.getPropertyName()));
            } catch (Exception e) {
                if (method != null) {
                    method.invoke(t, inject.getFieldName(), properties.get(inject.getPropertyName()));
                }
            }
        }
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
