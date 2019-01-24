package com.greenfoxacademy.springherokutemplate;

import com.greenfoxacademy.springherokutemplate.email.SendEmail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringherokutemplateApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringherokutemplateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SendEmail sendEmail = new SendEmail();

        sendEmail.sendFromGMail("wrkpeter@gmail.com", "asdf", "asdf");
    }
}

