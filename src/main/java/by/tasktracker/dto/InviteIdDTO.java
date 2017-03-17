package by.tasktracker.dto;

import javax.validation.constraints.NotNull;


public class InviteIdDTO {

    @NotNull
    private String inviteId;

    public String getInviteId() {
        return inviteId;
    }

    public void setInviteId(String inviteId) {
        this.inviteId = inviteId;
    }
}
