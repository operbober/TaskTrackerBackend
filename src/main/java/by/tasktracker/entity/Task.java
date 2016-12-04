package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Task extends NamedEntity {
    private String description;
    private Boolean status = false;

    @ManyToOne(targetEntity = TaskStatus.class)
    private TaskStatus taskStatus;

    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User creator;

    @ManyToOne(targetEntity = Project.class)
    @NotNull
    private Project project;

    @ManyToOne(targetEntity = User.class)
    private User developer;

    protected Task() {}

    public Task(String name, String description, User creator, Project project) {
        super(name);
        this.description = description;
        this.creator = creator;
        this.project = project;
    }

    public Task(String name, String description, User creator, Project project, User developer) {
        super(name);
        this.description = description;
        this.creator = creator;
        this.project = project;
        this.developer = developer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }
}
