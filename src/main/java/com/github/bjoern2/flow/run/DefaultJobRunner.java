package com.github.bjoern2.flow.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.github.bjoern2.flow.Job;
import com.github.bjoern2.flow.Task;
import com.github.bjoern2.flow.utils.JobUtils;

public class DefaultJobRunner implements JobRunner {

    private ExecutorService pool = Executors.newFixedThreadPool(5);
    
    @Override
    public void run(final Job job, Properties properties, List<String> taskIds) throws Exception {
        if (job == null) {
            return;
        }
        
        if (taskIds == null) {
            return;
        }
        
        Properties props = job.getProperties();
        props.putAll(properties);
        
        
        final List<Callable<List<String>>> callables = new ArrayList<Callable<List<String>>>();
        final List<Task<?>> tasks = JobUtils.findTasks(job, taskIds);
        for (Task<?> task : tasks) {
            callables.add(new TaskCallable(task, props));
        }
        final List<Future<List<String>>> results = pool.invokeAll(callables);
        
        for (Future<List<String>> result : results) {
            List<String> nexts = result.get();
            run(job, properties, nexts);
        }
    }
    

}
