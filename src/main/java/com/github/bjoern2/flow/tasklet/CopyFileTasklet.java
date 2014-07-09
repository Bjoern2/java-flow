package com.github.bjoern2.flow.tasklet;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class CopyFileTasklet implements Tasklet {

    private File source;

    private File target;
    
    @Override
    public String execute() throws Throwable {
        FileUtils.copyFileToDirectory(source, target, true);
        return "SUCCESS";
    }

    public void setSource(File source) {
        this.source = source;
    }

    public void setTarget(File target) {
        this.target = target;
    }

}
