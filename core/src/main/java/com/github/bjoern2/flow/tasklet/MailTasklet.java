package com.github.bjoern2.flow.tasklet;

import com.github.bjoern2.flow.model.Tasklet;

public class MailTasklet implements Tasklet {

    private String from;
    private String to;
    private String subject;
    private String message;
    
    @Override
    public String execute() throws Throwable {
        
        return null;
    }

}
