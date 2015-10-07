package controllers;

import play.mvc.*;
import play.data.*;
import models.*;


import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    public Result index() {
        return redirect(routes.Application.tasks());
    }

    public Result tasks() {
        return ok(
                views.html.index.render(Task.all(), taskForm)
        );
    }

    public Result newTask() {
        Form<Task> filledForm = taskForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    views.html.index.render(Task.all(), filledForm)
            );
        } else {
            Task.create(filledForm.get());
            return redirect(routes.Application.tasks());
        }
    }

    public Result deleteTask(Long id) {
        Task.delete(id);
        return redirect(routes.Application.tasks());
    }

    static Form<Task> taskForm = form(Task.class);

    public Result login() {
        return ok(
                views.html.login.render(form(Login.class))
        );
    }

    public Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        return ok();
    }

    public static class Login {

        public String email;
        public String password;

    }

}

