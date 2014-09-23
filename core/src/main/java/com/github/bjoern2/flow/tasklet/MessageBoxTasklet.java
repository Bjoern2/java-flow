package com.github.bjoern2.flow.tasklet;

import javax.swing.JOptionPane;

import com.github.bjoern2.flow.model.Tasklet;

public class MessageBoxTasklet implements Tasklet {

	private String message;
	private String title;
	
	
	@Override
	public String execute() throws Throwable {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
		return "SUCCESS";
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void setTitle(String title) {
		this.title = title;
	}

}
