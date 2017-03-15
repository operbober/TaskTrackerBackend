package by.tasktracker.dto;

/**
 * DTO for delete project
 *
 * Created by malets on 2/9/2017.
 */
public class DeleteProjectDTO extends ProjectIdDTO {

    private String deleteCode;

    public String getDeleteCode() {
        return deleteCode;
    }

    public void setDeleteCode(String deleteCode) {
        this.deleteCode = deleteCode;
    }
}
