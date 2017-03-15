package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Project extends NamedEntity {

    @Size(max = 300)
    private String description;

    @ManyToOne(targetEntity = User.class)
    @NotNull
    private User owner;

    @JsonIgnore
    private String deleteCode = null;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date creationDate;

    @JsonIgnore
    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    private Set<User> members;

    public Project() {}

    public Project(String name, String description, User owner) {
        super(name);
        this.description = description;
        this.owner = owner;
        this.creationDate = new Date();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void generateDeleteCode() {
        if (deleteCode == null) {
            this.deleteCode = UUID.randomUUID().toString();
        }
    }

    public String getDeleteCode() {
        return deleteCode;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
}
