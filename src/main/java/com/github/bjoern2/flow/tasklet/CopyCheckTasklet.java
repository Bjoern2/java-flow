package com.github.bjoern2.flow.tasklet;

import java.io.File;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

/**
 * Created by bjoern on 06.07.2014.
 */
public class CopyCheckTasklet implements Tasklet {

    private File source;

    private File target;
    
    private boolean checkContent = true;

    @Override
    public String execute() throws Throwable {
        if (target.exists()) {
            if (checkContent) {
                if (FileUtils.contentEquals(source, target)) {
                    final String message = "Ziel-Datei bereits vorhanden. Beide Dateien sind identisch. Überscheiben?";
                    final String title = "";
                    final int optionType = JOptionPane.YES_NO_CANCEL_OPTION;
                    final int messageType = JOptionPane.WARNING_MESSAGE;
                    int result = JOptionPane.showConfirmDialog(null, message, title, optionType, messageType);
                    if (result == JOptionPane.YES_OPTION) {
                        return "SUCCESS";
                    } else if (result == JOptionPane.NO_OPTION) {
                        return "SKIP";
                    } else {
                        return "FAILURE";
                    }
                }
            }
            
            final String message = "Ziel-Datei bereits vorhanden. Überschreiben?";
            final String title = "";
            final int optionType = JOptionPane.YES_NO_CANCEL_OPTION;
            final int messageType = JOptionPane.WARNING_MESSAGE;
            int result = JOptionPane.showConfirmDialog(null, message, title, optionType, messageType);
            if (result == JOptionPane.YES_OPTION) {
                return "SUCCESS";
            } else if (result == JOptionPane.NO_OPTION) {
                return "SKIP";
            } else {
                return "FAILURE";
            }
        }
        return "SUCCESS";
        
        //FileUtils2.copy(src, target, createDir)
        //FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
        //FileUtils.moveFileToDirectory(source, target, true);

    }

    public void setSource(File source) {
        this.source = source;
    }

    public void setTarget(File target) {
        this.target = target;
    }

    public void setCheckContent(boolean checkContent) {
        this.checkContent = checkContent;
    }
}
