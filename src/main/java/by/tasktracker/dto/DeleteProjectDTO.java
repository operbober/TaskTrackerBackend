package by.tasktracker.dto;

import javax.validation.constraints.NotNull;

/**
 * DTO for delete project
 *
 * Created by malets on 2/9/2017.
 */
public class DeleteProjectDTO {

    @NotNull
    private String id;

    private String deleteCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeleteCode() {
        return deleteCode;
    }

    public void setDeleteCode(String deleteCode) {
        this.deleteCode = deleteCode;
    }
}
