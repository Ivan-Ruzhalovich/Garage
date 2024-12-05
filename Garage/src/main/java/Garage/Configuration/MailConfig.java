//package Garage.Configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class MailConfig {
//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Value("${spring.mail.username}")
//    private String username;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    @Value("${spring.mail.port}")
//    private String port;
//
//    @Value("${spring.mail.protocol}")
//    private String protocol;
//
//    @Value("${mail.debug}")
//    private String debug;
//
//    @Bean
//    public JavaMailSender getNewJavaMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        Properties mailSenderProperties = mailSender.getJavaMailProperties();
//        mailSenderProperties.setProperty("spring.mail.host","smtp.mail.ru");
//        mailSenderProperties.setProperty("spring.mail.username","ruzhalovich@bk.ru");
//        mailSenderProperties.setProperty("spring.mail.password","170494jggjam");
//        mailSenderProperties.setProperty("spring.mail.port","465");
//        mailSenderProperties.setProperty("spring.mail.protocol","smtps");
//        mailSenderProperties.setProperty("mail.debug","true");
//        mailSender.setHost(host);
//        mailSender.setPort(Integer.parseInt(port));
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//        mailSenderProperties.setProperty("mail.transport.protocol",protocol);
//        mailSenderProperties.setProperty("mail.debug",debug);
//
//        return mailSender;
//    }
//}
