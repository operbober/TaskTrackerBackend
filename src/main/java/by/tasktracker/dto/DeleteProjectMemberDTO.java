package by.tasktracker.dto;

import javax.validation.constraints.NotNull;

public class DeleteProjectMemberDTO extends ProjectIdDTO{

    @NotNull
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
