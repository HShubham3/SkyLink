package com.scm.skylink;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.skylink.services.EmailService;

@SpringBootTest
class SkylinkApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService emailService;

	@Test
	void sendMail() {
		emailService.sendEmail(
				"hadoleshubh22@gmail.com",
				"Testing mail",
				"It is a testing mail..");
		System.out.println("sent mail");
	}

}
