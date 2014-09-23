package com.github.bjoern2.flow.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.github.bjoern2.flow.model.Job;
import com.github.bjoern2.flow.model.Task;

public class JobUtils {

    public static List<Task<?>> findTasks(Job job, String... taskIds) {
        return findTasks(job, Arrays.asList(taskIds));
    }
    
    public static List<Task<?>> findTasks(Job job, Collection<String> taskIds) {
        List<Task<?>> tasks = new ArrayList<Task<?>>();
        for (String taskId : taskIds) {
            for (Task<?> task : job.getTasks()) {
                if (task.getId().equals(taskId)) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }
    
}
