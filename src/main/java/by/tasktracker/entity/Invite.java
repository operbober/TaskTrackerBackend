package by.tasktracker.entity;

import by.tasktracker.entity.superclass.CommonEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Invite extends CommonEntity {

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Project.class)
    private Project project;

    @ManyToOne(targetEntity = InviteStatus.class)
    private InviteStatus status;

    public Invite() {

    }

    public Invite(User user, Project project, InviteStatus status) {
        this.user = user;
        this.project = project;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public InviteStatus getStatus() {
        return status;
    }

    public void setStatus(InviteStatus status) {
        this.status = status;
    }
}
