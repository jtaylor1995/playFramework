package models;

import java.util.*;

//import play.db.ebean.*;
import com.avaje.ebean.Model;
import play.data.validation.Constraints.*;

import javax.persistence.*;


@Entity
public class Task extends Model {

    @Id
    public Long id;
    public String title;
    public boolean done = false;
    public Date dueDate;
    @ManyToOne
    public User assignedTo;
    public String folder;
    @ManyToOne
    public Project project;

    public static Model.Finder<Long,Task> find = new Model.Finder(Task.class);

    public static List<Task> findTodoInvolving(String user) {
        return find.fetch("project").where()
                .eq("done", false)
                .eq("project.members.email", user)
                .findList();
    }

    public static Task create(Task task, Long project, String folder) {
        task.project = Project.find.ref(project);
        task.folder = folder;
        task.save();
        return task;
    }
}