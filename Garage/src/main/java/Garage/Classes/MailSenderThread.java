package Garage.Classes;

import Garage.entity.User;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Properties;

@Component
public class MailSenderThread implements Runnable{

    private User user;

    private String password;

    public MailSenderThread(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void run() {
        JavaMailSenderImpl javaMailSender = getMailSender();
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper mesage = new MimeMessageHelper(mimeMessage,"UTF-8");
            mesage.setTo(user.getEmail());
            mesage.setFrom(Objects.requireNonNull(javaMailSender.getUsername()));
            mesage.setSubject("Активация аккаунта Garage");
            mesage.setText(MailMessage.getMessage(user.getSurname(),user.getName(),user.getActivationCode()
                    ,user.getUserNameAuth().getUsername(),password),true);
        };
        javaMailSender.send(preparator);
    }

    private static JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setDefaultEncoding("utf-8");
        javaMailSender.setHost("smtp.mail.ru");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("ruzhalovich@bk.ru");
        javaMailSender.setPassword("24ZegXGi1puT64mQgaNS");
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable","false");
        javaMailProperties.put("mail.smtp.auth","true");
        javaMailProperties.put("mail.transport.protocol","smtps");
        javaMailProperties.put("mail.debug","true");
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }
}
