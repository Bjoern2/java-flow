package com.github.bjoern2.flow.mail;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import com.github.bjoern2.flow.model.Tasklet;
import com.github.bjoern2.flow.tasklet.TaskletStatus;

public class SendMailTasklet implements Tasklet {

	private int port;
	private String host;
	private String protocol;
	private String username;
	private String password;
	
	private String to;
	private String from;
	private String subject;
	private String text;

	@Override
	public String execute() throws Throwable {
		Email email = new SimpleEmail();
		email.setHostName(host);
		email.setSmtpPort(port);
		email.setAuthenticator(new DefaultAuthenticator(username, password));
		email.setSSLOnConnect(true);
		email.setFrom(from);
		email.setSubject(subject);
		email.setMsg(text);
		
		String[] tos = StringUtils.split(to, ",");

		for (String to : tos) {
			email.addTo(to);
		}
		email.send();

		return TaskletStatus.SUCCESS;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
