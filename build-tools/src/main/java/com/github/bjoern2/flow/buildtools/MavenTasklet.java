package com.github.bjoern2.flow.buildtools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;

import com.github.bjoern2.flow.model.Tasklet;

public class MavenTasklet extends AbstractBuildToolTasklet implements Tasklet {

	private String javaHome;
	private String mavenHome;
	private String directory;
	private List<String> goals = new ArrayList<String>();


	@Override
	protected List<String> command() {
		List<String> args = new ArrayList<String>();
		if (SystemUtils.IS_OS_WINDOWS) {
			args.add(mavenHome + "/bin/mvn.bat");
		} else {
			args.add(mavenHome + "/bin/mvn");
		}
		args.addAll(goals);
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
	
	public void setGoals(List<String> goals) {
		this.goals = goals;
	}

	public void setMavenHome(String mavenHome) {
		this.mavenHome = mavenHome;
	}

	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

}
