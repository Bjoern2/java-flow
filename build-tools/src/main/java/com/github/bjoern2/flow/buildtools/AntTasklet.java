package com.github.bjoern2.flow.buildtools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;

import com.github.bjoern2.flow.model.Tasklet;

public class AntTasklet extends AbstractBuildToolTasklet implements Tasklet {

	private String javaHome;
	private String antHome;
	private String directory;
	private List<String> tasks = new ArrayList<String>();
	private String buildFile;
	
	@Override
	protected List<String> command() {
		List<String> args = new ArrayList<String>();
		if (SystemUtils.IS_OS_WINDOWS) {
			args.add(antHome + "/bin/ant.bat");
		} else {
			args.add(antHome + "/bin/ant");
		}
		args.addAll(tasks);
		
		if (buildFile != null) {
			// --build-file
			args.add("-f " + buildFile);
		}
		return args;
	}
	
	@Override
	protected String directory() {
		return directory;
	}

	@Override
	protected Map<String, String> environment() {
		Map<String, String> env = new HashMap<String, String>();
		if (javaHome != null) {
			env.put("JAVA_HOME", javaHome);
		}
		
		return env;
	}
	
	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}

	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void setAntHome(String antHome) {
		this.antHome = antHome;
	}

	public void setBuildFile(String buildFile) {
		this.buildFile = buildFile;
	}

}
