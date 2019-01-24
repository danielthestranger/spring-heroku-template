package com.greenfoxacademy.springherokutemplate.email;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class SendEmail {

  private String USER_NAME;  // GMail user name (just the part before "@gmail.com")
  private String PASSWORD; // GMail password

  public SendEmail() {
    this.USER_NAME = System.getenv().get("EMAIL_NAME");
    this.PASSWORD = System.getenv().get("EMAIL_PASSWORD");
  }


  public void sendFromGMail(String to, String subject, String body) {
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.user", USER_NAME);
    props.put("mail.smtp.password", PASSWORD);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
      message.setFrom(new InternetAddress(USER_NAME));
      InternetAddress toAddress = new InternetAddress(to);
      message.addRecipient(Message.RecipientType.TO, toAddress);
      message.setSubject(subject);
      message.setText(body);
      Transport transport = session.getTransport("smtp");
      transport.connect("smtp.gmail.com", USER_NAME, PASSWORD);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    } catch (AddressException ae) {
      ae.printStackTrace();
    } catch (MessagingException me) {
      me.printStackTrace();
    }
  }
}