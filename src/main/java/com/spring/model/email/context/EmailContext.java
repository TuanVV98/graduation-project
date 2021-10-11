package com.spring.model.email.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailContext {

    String from = "Email@gmail.com";
    String to ;
    String subject ;
    String body ;
    List<String> cc = new ArrayList<>();
    List<String> bcc = new ArrayList<>();
    List<File> file = new ArrayList<>();

    public EmailContext(String to, String subject, String body) {
        super();
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public EmailContext(String to, String subject, String body, List<File> file) {
        super();
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.file = file;
    }

    public EmailContext(String to, String subject, String body, List<String> cc, List<File> file) {
        super();
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.cc = cc;
        this.file = file;
    }

}
