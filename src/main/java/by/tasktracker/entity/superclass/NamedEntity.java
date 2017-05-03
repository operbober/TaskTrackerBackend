package by.tasktracker.entity.superclass;


import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedEntity extends CommonEntity {

    private String name;

    protected NamedEntity() {
    }

    public NamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
