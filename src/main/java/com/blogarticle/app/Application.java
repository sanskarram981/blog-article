package com.blogarticle.app;

import com.blogarticle.app.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String attachPath = "F:\\com.blog-article\\src\\main\\resources\\static\\pdfs\\SUM_410825175038_260004922270_20240102.pdf";
        this.emailService.sendMessageWithAttachment("sanskarram992@gmail.com","priyanka17462@gmail.com","this is testing springboot emailservice application","testing our developed springboot application mail service","sanskarram992@gmail.com",attachPath);
	}
}
