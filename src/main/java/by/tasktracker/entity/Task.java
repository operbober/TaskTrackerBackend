package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class Task extends NamedEntity {

    private String description;

    @JsonFormat(pattern = "MM/dd/yyyy")
    @NotNull
    private Date creationDate;

    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User owner;

    @ManyToOne(targetEntity = Project.class)
    @NotNull
    private Project project;

    @ManyToMany(targetEntity = TaskTag.class, fetch = FetchType.EAGER)
    private Set<TaskTag> tags;

    protected Task() {}

    public Task(String name, String description, User owner, Project project) {
        super(name);
        this.description = description;
        this.project = project;
        this.owner = owner;
        this.creationDate = new Date();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<TaskTag> getTags() {
        return tags;
    }

    public void setTags(Set<TaskTag> tags) {
        this.tags = tags;
    }
}
