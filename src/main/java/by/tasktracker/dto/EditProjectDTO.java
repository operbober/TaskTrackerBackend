package by.tasktracker.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for edit exist project.
 *
 * Created by malets on 1/26/2017.
 */
public class EditProjectDTO {

    @NotNull
    private String id;

    @Size(min = 4, max = 16)
    private String name;

    @Size(max = 250)
    private String description;

    @AssertTrue(message = "all editable parameters is null")
    private boolean isFieldsNotNull() {
        return description != null || name != null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
