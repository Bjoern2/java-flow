package com.github.bjoern2.flow.run;

import java.util.List;
import java.util.Properties;

import com.github.bjoern2.flow.Task;

public class DefaultTaskRunner implements TaskRunner {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<String> run(Task task, Properties properties) {
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
