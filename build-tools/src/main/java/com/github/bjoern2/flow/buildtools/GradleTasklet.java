package com.github.bjoern2.flow.buildtools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.SystemUtils;

import com.github.bjoern2.flow.model.Tasklet;

public class GradleTasklet extends AbstractBuildToolTasklet implements Tasklet {

	private String javaHome;
	private String gradleHome;
	private String directory;
	private List<String> tasks = new ArrayList<String>();
	
	private boolean noRebuild = false;
	private String buildFile;
	private String settingsFile;
	private boolean _continue = false;
	private Map<String, String> systemProps;
	private String gradleUserHome;
	
	@Override
	protected List<String> command() {
		List<String> args = new ArrayList<String>();
		if (SystemUtils.IS_OS_WINDOWS) {
			args.add(gradleHome + "/bin/gradle.bat");
		} else {
			args.add(gradleHome + "/bin/gradle");
		}
		args.addAll(tasks);
		if (noRebuild) {
			args.add("-a"); // --no-rebuild
		}
		
		if (buildFile != null) {
			// --build-file
			args.add("-b " + buildFile);
		}
		if (settingsFile != null) {
			// --settings-file
			args.add("-c " + settingsFile);
		}
		if (_continue) {
			args.add("--continue");
		}
		
		if (systemProps != null) {
			for (Entry<String, String> prop : systemProps.entrySet()) {
				args.add("-D" + prop.getKey() + "=" + prop.getValue());
			}
		}
		
		if (gradleUserHome != null) {
			args.add("-g " + gradleUserHome); 
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

	public void setGradleHome(String gradleHome) {
		this.gradleHome = gradleHome;
	}

	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

}
