package com.github.bjoern2.flow.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

import com.github.bjoern2.flow.model.Next;
import com.github.bjoern2.flow.model.Task;
import com.github.bjoern2.flow.model.Tasklet;

public class TaskCallable implements Callable<List<String>>{

    private Task<Tasklet> task;
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
        List<String> refs = new ArrayList<String>();
        
        final List<Next> nexts = task.getNexts();
        
        for (Next next : nexts) {
            if (next.getOn().equals(result)) {
                refs.add(next.getRef());
            }
        }
        
        return refs;
    }

}
