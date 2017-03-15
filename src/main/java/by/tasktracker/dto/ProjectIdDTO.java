package by.tasktracker.dto;

import javax.validation.constraints.NotNull;


public class ProjectIdDTO {

    @NotNull
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
