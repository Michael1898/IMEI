package models;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;


public class Register{

    @Email(message= "Некорректный адрес электронной почты")
    @Required(message = "Обязательное поле")
    public String email;

    @Required(message = "Обязательное поле")
    public String password;

    @Required(message = "Обязательное поле")
    public String name;

    // Валидация
    public String validate() {
        boolean available= User.emailAvailable(email);
        return (available==true) ? null : "Пользователь с данным почтовым адресом уже зарегистрирован";
    }
}