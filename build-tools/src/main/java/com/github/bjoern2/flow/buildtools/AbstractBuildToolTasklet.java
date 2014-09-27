package com.github.bjoern2.flow.buildtools;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.zeroturnaround.exec.ProcessExecutor;

import com.github.bjoern2.flow.model.Tasklet;
import com.github.bjoern2.flow.tasklet.TaskletStatus;

public abstract class AbstractBuildToolTasklet implements Tasklet {

	
	@Override
	public String execute() throws Throwable {
		ProcessExecutor pe = new ProcessExecutor();
		pe.environment(environment());
		pe.command(command());
		String dir = directory();
		if (StringUtils.isNotBlank(dir)) {
			pe.directory(new File(dir));
		}
		pe.redirectOutput(System.out);
		pe.redirectError(System.err);
		int result = pe.execute().getExitValue();
		
		return result == 0 ? TaskletStatus.SUCCESS : TaskletStatus.FAILURE;
	}
	
	protected abstract List<String> command();
	
	protected abstract String directory();
	
	protected abstract Map<String, String> environment();

}
