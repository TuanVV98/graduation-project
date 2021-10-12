package com.spring.service.email;

import com.spring.model.email.context.EmailContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
@SpringBootApplication
@EnableScheduling
public class MailServices {

    @Autowired
    JavaMailSender sender;
    List<MimeMessage> queue = new ArrayList<>();

    public void push(String to, String subject, String body) throws MessagingException {
        EmailContext mail = new EmailContext(to, subject, body);
        this.push(mail);
    }


    public void push(EmailContext mailModel) throws MessagingException {
        MimeMessage mess = sender.createMimeMessage();

        MimeMessageHelper mailhellper = new MimeMessageHelper(mess);
        mailhellper.setFrom(mailModel.getFrom());
        mailhellper.setSubject(mailModel.getSubject());
        mailhellper.setTo(mailModel.getTo());
        mailhellper.setText(mailModel.getBody());
        for (String ccAdress : mailModel.getCc()) {
            mailhellper.addCc(ccAdress);
        }
        for (String bccAdress : mailModel.getBcc()) {
            mailhellper.addBcc(bccAdress);
        }
        for (File file : mailModel.getFile()) {
            mess.setFileName(file.getName());
//			mailhellper.addAttachment(file.getName(), file);
        }
        queue.add(mess);
    }

    @Scheduled(fixedDelay = 2000)
    public void RunEmail() {
        try {
            while (!queue.isEmpty()) {
                MimeMessage mailModelFirst = queue.remove(0);
                sender.send(mailModelFirst);
                System.out.println("sent email secess");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sent Email");
        }
    }
}
