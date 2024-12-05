package Garage.Classes;

import org.springframework.stereotype.Component;

@Component
public class MailMessage {

    public static String getMessage(String surname, String name, String activationCode
            , String login, String password){
        String message = String.format(
                        "<p>%s %s, добро пожаловать. Спасибо за регистрацию!</p>"+
                                "<p>Ваш логин : %s</p><p>Ваш пароль : %s</p>"+
                                "<p>Для активации аккаунта пожалуйста перейдите по " +
                                "<a href = \"http://localhost:8080/Garage/activate/%s\"> ссылке.</a></p>" +
                                "<p>Вы будете перенапралены на страницу входа. Используйте свой логин и пароль.</p>" +
                                "<p>Приятного пользования прилижением Garage!</p>"
                        ,surname,name,login,password,activationCode);
        return message;
    }
}
