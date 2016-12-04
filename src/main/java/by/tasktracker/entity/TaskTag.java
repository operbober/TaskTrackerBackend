package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;

import javax.persistence.Entity;

@Entity
public class TaskTag extends NamedEntity {

    public TaskTag() {
    }

    public TaskTag(String name) {
        super(name);
    }

    @Override
    public int hashCode() {
        return String.valueOf(this.getName()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) && this.getName().equals(((TaskTag) obj).getName());
    }
}

