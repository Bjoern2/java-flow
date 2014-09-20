package com.github.bjoern2.flow.run;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

import com.github.bjoern2.flow.Task;

public class TaskCallable implements Callable<List<String>>{

    private Task task;
    private Properties properties;
    
    public TaskCallable(Task task, Properties properties) {
        this.task = task;
        this.properties = properties;
    }
    
    @Override
    public List<String> call() throws Exception {
        if (task.getInjector() != null) {
            task.getInjector().inject(task.getTasklet(), properties);
        }
        String result;
        try {
            result = task.getTasklet().execute();
        } catch (Throwable e) {
            result = "EXCEPTION";
            properties.put("EXCEPTION", e);
        }
        if (task.getEjector() != null) {
            task.getEjector().eject(task.getTasklet(), properties);
        }
//      nexts.get(result).run();
        return task.getNexts(result);
    }

}
