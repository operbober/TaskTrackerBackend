package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;

import javax.persistence.Entity;

@Entity
public class InviteStatus extends NamedEntity {

    public static final String OPEN = "Open";
    public static final String CLOSE = "Close";

    public InviteStatus() {
    }

    public InviteStatus(String name) {
        super(name);
    }
}
