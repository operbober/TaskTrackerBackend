package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project extends NamedEntity {
    private String description;

    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User creator;

    @JsonIgnore
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    protected Project() {}

    public Project(String name, String description, User creator) {
        super(name);
        this.description = description;
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
