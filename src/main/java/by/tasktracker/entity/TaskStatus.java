package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;

import javax.persistence.Entity;

@Entity
public class TaskStatus extends NamedEntity {

    public static final String OPEN = "Open";
    public static final String IN_PROGRESS = "In progress";
    public static final String CLOSED = "Closed";

    public TaskStatus() {
    }

    public TaskStatus(String name) {
        super(name);
    }
}
