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
        return TODO;
    }

    public Result deleteTask(Long id) {
        return TODO;
    }

    static Form<Task> taskForm = Form.form(Task.class);

}
