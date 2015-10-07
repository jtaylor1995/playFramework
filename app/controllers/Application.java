package controllers;

import play.mvc.*;
import play.data.*;
import models.*;


import views.html.*;

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

    static Form<Task> taskForm = Form.form(Task.class);

    public Result login() {
        return ok(
                views.html.login.render()
        );
    }

}
