package by.tasktracker.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for add new project.
 *
 * Created by malets on 1/24/2017.
 */
public class AddProjectDTO {

    @NotNull
    @Size(min = 4, max = 16)
    private String name;

    @Size(max = 250)
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
