package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Project extends NamedEntity {
    private String description;

    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User creator;

    @ManyToMany(targetEntity = ProjectTag.class)
    private Set<ProjectTag> tags;

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

    public Set<ProjectTag> getTags() {
        return tags;
    }

    public void setTags(Set<ProjectTag> tags) {
        this.tags = tags;
    }
}
