package com.github.bjoern2.flow.tasklet;

public class GreetingsTasklet implements Tasklet {

	private String message = "";
	
	@Override
	public String execute() throws Throwable {
		System.out.println(message);
		return "SUCCESS";
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
