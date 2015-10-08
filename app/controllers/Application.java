package controllers;

import play.mvc.*;
import play.data.*;
import models.*;


import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result index() {
        return redirect(routes.Application.tasks());
    }

    public static Result tasks() {
        return ok(
                views.html.index.render(Task.all(), taskForm)
        );
    }

    public static Result newTask() {
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

    public static Result deleteTask(Long id) {
        Task.delete(id);
        return redirect(routes.Application.tasks());
    }

    static Form<Task> taskForm = form(Task.class);

    public static Result login() {
        return ok(
                views.html.login.render(form(Login.class))
        );
    }

//    public static Result authenticate() {
//        Form<Login> loginForm = form(Login.class).bindFromRequest();
//        if (loginForm.hasErrors()) {
//            return badRequest(views.html.login.render(loginForm));
//        } else {
//            session().clear();
//            session("email", loginForm.get().email);
//            return redirect(
//                    routes.Application.index()
//            );
//        }
//    }

    public static Result authenticate() {
        return redirect(routes.Application.index());
    }

    public static Boolean authenticateDetails(String email, String password) {
        if (email.equals("James@Taylor.com") && password.equals("password")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static class Login {

        public String email;
        public String password;


        public String validate() {
            if (Application.authenticateDetails(email, password) == Boolean.FALSE) {
                return "Invalid user or password";
            }
            else Application.authenticate();
            return null;
        }
    }

}

