package com.github.bjoern2.flow.buildtools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zeroturnaround.exec.ProcessExecutor;

import com.github.bjoern2.flow.model.Tasklet;
import com.github.bjoern2.flow.tasklet.TaskletStatus;

public class MavenTasklet implements Tasklet {

	private String javaHome;
	private String mavenHome;
	private List<String> goals = new ArrayList<String>();
	
	@Override
	public String execute() throws Throwable {
		List<String> args = new ArrayList<String>();
		args.add(mavenHome + "/bin/mvn.bat");
		args.addAll(goals);
		
		Map<String, String> env = new HashMap<String, String>();
		if (javaHome != null) {
			env.put("JAVA_HOME", javaHome);
		}
		
		ProcessExecutor pe = new ProcessExecutor();
		pe.environment(env);
		pe.command(args);
		pe.redirectOutput(System.out);
		pe.redirectError(System.err);
		int result = pe.execute().getExitValue();
		
//		final Process p = Runtime.getRuntime().exec(path + "/bin/maven", args.toArray(new String[0]));
//		
//		new Thread(new Runnable() {
//
//			public void run() {
//				try {
//					IOUtils.copy(p.getInputStream(), System.out);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		
//		int result = p.waitFor();
		
		return result == 0 ? TaskletStatus.SUCCESS : TaskletStatus.FAILURE;
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

}
