package controllers;

import controllers.routes;
import play.mvc.*;
import models.User;
import play.mvc.Http.Context;

public class AdminController extends Security.Authenticator {
    /**
     * Механизм аутентификации
     *
     * @param ctx контекст запроса.
     * @return строку-email для текущего пользователя, хранящуюся в сессии при аутентификации. В случае ее отсутствия возвращает null
     */
    @Override
    public String getUsername(Context ctx) {
        String email = ctx.session().get("email");
        if(email != null){
            if(User.find.byId(email).getAdmin())
                return "ok";
            else return null;
        }
        else return null;
    }

    /**
     * Перенаправление в случае неуспеха аутентификации. Как правило перенаправляет на форму логина
     *
     * @param ctx контекст
     * @return перенаправление на страницу логина
     */

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.HomeController.list());
	}
}