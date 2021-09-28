package com.spring.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailModel {
	String from = "TK@gmail.com";
	String to ;
	String subject ;
	String body ;
	List<String> cc = new ArrayList<>();
	List<String> bcc = new ArrayList<>();
	List<File> file = new ArrayList<>();

	public MailModel(String to, String subject, String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

	public MailModel(String to, String subject, String body, List<File> file) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.file = file;
	}

	public MailModel(String to, String subject, String body, List<String> cc, List<File> file) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.cc = cc;
		this.file = file;
	}

	
	
}
