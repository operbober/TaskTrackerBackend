package by.tasktracker.controller;

import by.tasktracker.dto.InviteDTO;
import by.tasktracker.dto.InviteIdDTO;
import by.tasktracker.entity.Invite;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.PermissionException;
import by.tasktracker.security.Permission;
import by.tasktracker.security.checker.InviteUserChecker;
import by.tasktracker.security.checker.InviteUserOrSenderChecker;
import by.tasktracker.security.checker.ProjectOwnerPermissionChecker;
import by.tasktracker.service.InviteService;
import by.tasktracker.service.ProjectService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/invites")
public class InviteController {

    @Autowired private InviteService service;
    @Autowired private ProjectService projectService;

    @RequestMapping(value = "/{page}/{size}", method = RequestMethod.GET)
    public Page<Invite> getMyInvites(@AuthenticationPrincipal User user,
                              @PathVariable(value = "page") int page,
                              @PathVariable(value = "size") int size){
        return service.getByUserId(user.getId(), page, size);
    }

    @RequestMapping(value = "/{projectId}/{page}/{size}",method = RequestMethod.GET)
    public Page<Invite> getProjectInvites(@AuthenticationPrincipal User user,
                                          @PathVariable(value = "projectId") String projectId,
                                          @PathVariable(value = "page") int page,
                                          @PathVariable(value = "size") int size) throws Exception {
        Project project = projectService.get(projectId);
        if (project == null) {
            throw new NotFoundException("Project not found!");
        } else if (!project.getOwner().getId().equals(user.getId())) {
            throw new PermissionException("Permission denied!");
        }
        return service.getByProjectId(projectId, page, size);
    }

    @Permission(ProjectOwnerPermissionChecker.class)
    @RequestMapping(method = RequestMethod.POST)
    public Invite invite(@AuthenticationPrincipal User user,
                      @RequestBody @Valid InviteDTO inviteDTO) throws Exception {
        return service.save(inviteDTO);
    }

    @Permission(InviteUserChecker.class)
    @RequestMapping(method = RequestMethod.PUT)
    public Invite confirm(@AuthenticationPrincipal User user,
                        @RequestBody InviteIdDTO inviteIdDTO) throws Exception {
        return service.confirm(inviteIdDTO.getInviteId());
    }

    @Permission(InviteUserOrSenderChecker.class)
    @RequestMapping(method = RequestMethod.DELETE)
    public Invite close(@AuthenticationPrincipal User user,
                         @RequestBody InviteIdDTO inviteIdDTO) throws Exception {
        return service.close(inviteIdDTO.getInviteId());
    }


}
