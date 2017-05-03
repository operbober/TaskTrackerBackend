package by.tasktracker.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AddTaskDTO extends ProjectIdDTO {

    @NotNull
    @Size(min = 5, max = 100)
    private String name;

    @Size(max = 300)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
