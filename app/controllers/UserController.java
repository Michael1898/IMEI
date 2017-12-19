package controllers;

import io.ebean.PagedList;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import play.twirl.api.Html;
import router.Routes;
import views.html.*;

import javax.inject.Inject;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@Security.Authenticated(AdminController.class)
public class UserController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public UserController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */


    /*public Result index() {
        List<User> all = User.find.all();
        return ok(login.render("Your new application is ready.", all));
    }*/

    /**
     * Список фич
     * @return
     */
    //@Security.Authenticated(SecuredController.class)
    public Result list(){

        List<User> page = User.find.all();
        return ok(userList.render(page));
    }

    /**
     * Пустая форма для создания фичи
     * @return
     */
    public Result create(){
        Form<User> userForm = formFactory.form(User.class);
        return ok(userCreateForm.render(userForm));
    }

    /**
     * Обрабатывает форму создания
     * @return
     */
    public Result save(){
        Form<User> userForm = formFactory.form(User.class).bindFromRequest();
        if (userForm.hasErrors()){
            return badRequest(userCreateForm.render(userForm));
        } else {
            //получаем из формы
            User user = userForm.get();
            //System.out.println(userForm);
            //System.out.println(user);
            //сохраняем
            if(User.find.byId(user.getEmail())!=null)
                return badRequest(userCreateForm.render(userForm));
            user.save();

            return redirect(routes.UserController.list());
        }
    }

    /**
     * Форму заполненную данными
     * @param id
     * @return
     */
    public Result edit(String id){
        User user = User.find.byId(id);
        if (user == null){
            return notFound("404 NotFound");
        }
        Form<User> userForm = formFactory.form(User.class).fill(user);

        return ok(userEditForm.render(id, userForm));
    }

    /**
     * обновляет объект в СУБД
     * @param id
     * @return
     */
    public Result update(String id){
        Form<User> userForm = formFactory.form(User.class).bindFromRequest();
        if (userForm.hasErrors()){
            return badRequest(userEditForm.render(id, userForm));
        } else {
            User user1 = User.find.byId(id);
            if(user1 == null) return notFound();
            User user2 = userForm.get();
            String password = user1.getPassword();
            user1.delete();
            user2.savePassword(password);
            user2.save();
            return redirect(routes.UserController.list());
        }
    }

    /**
     * Удаление
     * @param id
     * @return
     */
    public Result delete(String id){
        User user = User.find.byId(id);
        if (user == null){
            return notFound();
        }

        user.delete();
        return redirect(routes.UserController.list());
    }
}
