package com.github.bjoern2.flow.tasklet.checkstyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.bjoern2.flow.model.Tasklet;

public class ReportCheckstyleTasklet implements Tasklet {

    private DataSource dataSource;
    
    private long jobId;
    private long runId;
    private long ignore;
    private long info;
    private long warning;
    private long error;
    
    @Override
    public String execute() throws Throwable {
        String sql = "INSERT INTO checkstyle_report(job_id, run_id, ignore, info, warning, error) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, jobId);
            stmt.setLong(2, runId);
            stmt.setLong(3, ignore);
            stmt.setLong(4, info);
            stmt.setLong(5, warning);
            stmt.setLong(6, error);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return "SUCCESS";
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

    public void setIgnore(long ignore) {
        this.ignore = ignore;
    }

    public void setInfo(long info) {
        this.info = info;
    }

    public void setWarning(long warning) {
        this.warning = warning;
    }

    public void setError(long error) {
        this.error = error;
    }

}
