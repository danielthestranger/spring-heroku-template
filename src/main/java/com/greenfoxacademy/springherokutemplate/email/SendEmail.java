package com.greenfoxacademy.springherokutemplate.email;

import org.springframework.beans.factory.annotation.Value;
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

  @Value("${EMAIL_NAME}")
  private String USER_NAME;  // GMail user name (just the part before "@gmail.com")
  @Value("${EMAIL_PASSWORD}")
  private String PASSWORD; // GMail password
  @Value("${HOST}")
  private String HOSTNAME;
  @Value("${PORT}")
  private String PORT;

  public SendEmail() {
  }


  public void sendFromGMail(String to, String subject, String body) {
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", HOSTNAME);
    props.put("mail.smtp.user", USER_NAME);
    props.put("mail.smtp.password", PASSWORD);
    props.put("mail.smtp.port", PORT);
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
      transport.connect(HOSTNAME, USER_NAME, PASSWORD);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    } catch (AddressException ae) {
      ae.printStackTrace();
    } catch (MessagingException me) {
      me.printStackTrace();
    }
  }
}