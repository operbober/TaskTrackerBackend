package by.tasktracker.entity;


import by.tasktracker.entity.superclass.CommonEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comment extends CommonEntity {
    @NotNull
    @Size(max = 200)
    private String text;

    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User creator;

    @ManyToOne(targetEntity = Task.class)
    @NotNull
    private Task task;

    protected Comment() {}

    public Comment(User creator, Task task, String text) {
        this.creator = creator;
        this.task = task;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
