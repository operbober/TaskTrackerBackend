package by.tasktracker.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskTagDTO extends ProjectIdDTO {

    @NotNull
    @Size(min = 3, max = 10)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
