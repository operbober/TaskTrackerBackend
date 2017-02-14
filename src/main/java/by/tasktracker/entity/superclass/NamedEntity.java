package by.tasktracker.entity.superclass;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class NamedEntity extends CommonEntity {
    @Column(name = "name")
    @NotNull
    @Size(min = 4, max = 16)
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
