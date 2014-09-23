package com.github.bjoern2.flow.tasklet;

import java.io.File;

import com.github.bjoern2.flow.model.Tasklet;

public class FileExistsTasklet implements Tasklet {

    private File file;
    
    @Override
    public String execute() throws Throwable {
        if (file.exists()) {
            return "EXISTS";
        }
        return "DOESNT_EXISTS";
    }

}
