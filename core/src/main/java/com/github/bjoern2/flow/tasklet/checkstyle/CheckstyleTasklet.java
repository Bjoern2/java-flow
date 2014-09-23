package com.github.bjoern2.flow.tasklet.checkstyle;

import java.io.File;

import javax.sql.DataSource;

import com.github.bjoern2.flow.model.Tasklet;

public class CheckstyleTasklet implements Tasklet {

    private File file;
    private DataSource dataSource;
    private long jobId;
    private long runId;
    
    @Override
    public String execute() throws Throwable {
        AnalyzeCheckstyleTasklet analyze = new AnalyzeCheckstyleTasklet();
        analyze.setFile(file);
        analyze.execute();
        
        ReportCheckstyleTasklet report = new ReportCheckstyleTasklet();
        report.setDataSource(dataSource);
        report.setJobId(jobId);
        report.setRunId(runId);
        report.setError(analyze.getError());
        report.setIgnore(analyze.getIgnore());
        report.setInfo(analyze.getInfo());
        report.setWarning(analyze.getWarning());
        return report.execute();
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public void setRunId(long runId) {
        this.runId = runId;
    }

}
