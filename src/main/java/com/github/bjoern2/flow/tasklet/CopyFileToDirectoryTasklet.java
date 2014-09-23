package com.github.bjoern2.flow.tasklet;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.github.bjoern2.flow.model.Tasklet;

public class CopyFileToDirectoryTasklet implements Tasklet {

    private File srcFile;

    private File destDir;
    
    private boolean preserveFileDate = true;
    
    @Override
    public String execute() throws Throwable {
        FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
        return "SUCCESS";
    }

    public void setSrcFile(File srcFile) {
        this.srcFile = srcFile;
    }

    public void setDestDir(File destDir) {
        this.destDir = destDir;
    }

    public void setPreserveFileDate(boolean preserveFileDate) {
        this.preserveFileDate = preserveFileDate;
    }


}
