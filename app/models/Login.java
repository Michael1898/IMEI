package models;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;



public class Login {

    @Email(message = "Некорректный адрес электронной почты")
    @Required(message = "Обязательное поле")
    public String email;

    @Required(message = "Обязательное поле")
    public String password;

    // Валидация формы
    public String validate() {
        return User.authenticate(email, password);
    }
}