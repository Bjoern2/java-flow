package com.github.bjoern2.flow.run;

import java.util.List;
import java.util.Properties;

import com.github.bjoern2.flow.model.Job;

public interface JobRunner {

    void run(Job job, Properties properties, List<String> taskIds) throws Exception;
    
}
