package com.github.bjoern2.flow.tasklet.checkstyle;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.github.bjoern2.flow.model.Tasklet;

public class AnalyzeCheckstyleTasklet implements Tasklet {

    private File file;
    
    private CheckstyleSAXHandler handler;
    
    @Override
    public String execute() throws Throwable {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        
        handler = new CheckstyleSAXHandler();
        
        saxParser.parse(file, handler);
        
        
        System.out.println(handler.getWarning());
        
        
//        String sql = "INSERT INTO checkstyle_report(job_id, run_id, ignore, info, warning, error) VALUES (:jobId, :runId, :ignore, :info, :warning, :error)";
        
        return null;
    }
    
    public static void main(String[] args) throws Throwable {
        AnalyzeCheckstyleTasklet t = new AnalyzeCheckstyleTasklet();
        t.setFile(new File("D:\\bjoern\\repos\\java-flow\\build\\reports\\checkstyle\\main.xml"));
        t.execute();
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public long getIgnore() {
        return handler.getIgnore();
    }

    public long getInfo() {
        return handler.getInfo();
    }

    public long getWarning() {
        return handler.getWarning();
    }

    public long getError() {
        return handler.getError();
    }
    

}
