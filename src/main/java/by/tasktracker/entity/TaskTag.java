package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TaskTag extends NamedEntity {

    @ManyToOne(targetEntity = Project.class)
    private Project project;

    public TaskTag() {
    }

    public TaskTag(String name, Project project) {
        super(name);
        this.project = project;
    }

    @Override
    public int hashCode() {
        return String.valueOf(this.getName()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) && this.getName().equals(((TaskTag) obj).getName());
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

