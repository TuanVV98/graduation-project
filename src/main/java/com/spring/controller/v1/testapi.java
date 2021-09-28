package com.spring.controller.v1;


import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.services.MailServices;

@Controller
public class testapi {
	@Autowired
	MailServices sv;
	@GetMapping("test")
	@ResponseBody
	public String get() throws MessagingException {
		sv.push("binhhtph11879@fpt.edu.vn", "Mail", "test Email");
		sv.push("datnvph11876@fpt.edu.vn", "Mail", "test Email");
		sv.push("kienntph11878@fpt.edu.vn", "Mail", "test Email");
		sv.push("datntph11793@fpt.edu.vn", "Mail", "test Email");
		sv.push("tuanvvph11914@fpt.edu.vn", "Mail", "test Email");
		sv.push("khaiph11867@fpt.edu.vn", "Mail", "test Email");
		return "sucess";
	}
	@GetMapping("testfile")
	@ResponseBody
	public String getfile() throws MessagingException {
		List<File> l = new ArrayList<File>();
		File f = new  File("H:\\background\\images.jpg");
		System.out.println(f.getName());
		l.add(f);
		sv.push(new MailModel("binhhtph11879@fpt.edu.vn", "Mail", "test file email", l));
		sv.push(new MailModel("tuanvvph11914@fpt.edu.vn", "Mail", "test file email", l));
		sv.push(new MailModel("datnvph11876@fpt.edu.vn", "Mail", "test file email", l));
		sv.push(new MailModel("khaiph11867@fpt.edu.vn", "Mail", "test file email", l));
		sv.push(new MailModel("kienntph11878@fpt.edu.vn", "Mail", "test file email", l));
		sv.push(new MailModel("datnvph11876@fpt.edu.vn", "Mail", "test file email", l));
		return "sucess";
	}
}
