package by.tasktracker.entity.superclass;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    protected CommonEntity() {
    }

    public CommonEntity(String id) {
        this.id = id;
    }

    public String  getId() {
        return id;
    }

}
