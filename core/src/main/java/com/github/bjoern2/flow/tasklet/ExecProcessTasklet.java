package com.github.bjoern2.flow.tasklet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.github.bjoern2.flow.model.Tasklet;

public class ExecProcessTasklet implements Tasklet {

//    private Logger logger = LoggerFactory.getLogger(ExecProcessTasklet.class);
    
    private String command;
    
    @Override
    public String execute() throws Throwable {
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
//            logger.info(line);
        }
        reader.close();
        
        int exitValue = p.exitValue();
        return exitValue == 0 ? "SUCCESS" : "ERROR";
    }

//    public void setLogger(Logger logger) {
//        this.logger = logger;
//    }

    public void setCommand(String command) {
        this.command = command;
    }

}
