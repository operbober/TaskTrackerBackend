package by.tasktracker.security.checker;

import by.tasktracker.dto.InviteIdDTO;
import by.tasktracker.entity.Invite;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.PermissionException;
import by.tasktracker.security.PermissionChecker;
import by.tasktracker.service.InviteService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class InviteUserOrSenderChecker implements PermissionChecker<InviteIdDTO> {

    @Autowired
    private InviteService inviteService;

    @Override
    public void checkPermission(User user, @Valid InviteIdDTO inviteIdDTO) throws Exception {

        Invite invite = inviteService.get(inviteIdDTO.getInviteId());
        if (invite == null) {
            throw new NotFoundException("Invite not found!");
        }
        if (!invite.getProject().getOwner().getId().equals(user.getId())
                && !invite.getUser().getId().equals(user.getId())) {
            throw new PermissionException("Permission denied!");
        }

    }
}
