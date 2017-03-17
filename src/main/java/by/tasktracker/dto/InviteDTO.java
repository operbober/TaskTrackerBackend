package by.tasktracker.dto;

import javax.validation.constraints.NotNull;


public class InviteDTO extends ProjectIdDTO{

    @NotNull
    private String username;

    public String getUsername() {
        return username;
    }
}
