package by.tasktracker.entity;

import by.tasktracker.entity.superclass.NamedEntity;

import javax.persistence.Entity;

@Entity
public class ProjectTag extends NamedEntity {

    public ProjectTag() {
    }

    public ProjectTag(String name) {
        super(name);
    }

    @Override
    public int hashCode() {
        return String.valueOf(this.getName()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) && this.getName().equals(((ProjectTag) obj).getName());
    }
}
