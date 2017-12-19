package models;

import controllers.AuthController;
import play.data.validation.Constraints;


public class ChangePassword{
    @Constraints.Required(message = "Введите текущий пароль")
    public String password;

    @Constraints.Required(message = "Введите новый пароль")
    public String newPassword;

    @Constraints.Required(message = "Подтвердите пароль пароль")
    public String newPassword2;


    // Валидация
    public String validate() {
        User user = AuthController.currentUser();
        if (user != null && newPassword.equals(newPassword2))
            return User.authenticate(user.getEmail(), password);

        return "Сперва необходимо аутентифицироваться";
    }
}