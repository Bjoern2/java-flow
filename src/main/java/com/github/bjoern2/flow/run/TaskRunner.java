package com.github.bjoern2.flow.run;

import java.util.List;
import java.util.Properties;

import com.github.bjoern2.flow.Task;

public interface TaskRunner {

    List<String> run(Task task, Properties properties);
    
}
