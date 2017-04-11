package by.tasktracker.controller;

import by.tasktracker.dto.*;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.security.Permission;
import by.tasktracker.security.checker.ProjectMemberChecker;
import by.tasktracker.security.checker.ProjectOwnerChecker;
import by.tasktracker.service.ProjectService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired private ProjectService service;

    @RequestMapping(value = "/my/{page}/{size}", method = RequestMethod.GET)
    public Page<Project> getMyProjects(@AuthenticationPrincipal User authorizedUser,
                                       @PathVariable(value = "page") int page,
                                       @PathVariable(value = "size") int size) {
        return service.getProjectsByMember(authorizedUser.getId(), page, size);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project addProject(@RequestBody @Valid AddProjectDTO projectDTO,
                              @AuthenticationPrincipal User authorizedUser) {
        return service.save(projectDTO, authorizedUser);
    }

    @Permission(ProjectOwnerChecker.class)
    @RequestMapping(method = RequestMethod.PUT)
    public Project editProject(@RequestBody @Valid EditProjectDTO projectDTO) throws Exception {
        return service.update(projectDTO);
    }

    @Permission(ProjectOwnerChecker.class)
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteProject(@RequestBody @Valid DeleteProjectDTO projectDTO) throws Exception {
        if(projectDTO.getDeleteCode() == null) {
            service.sendDeleteCode(projectDTO.getProjectId());
        } else
            service.delete(projectDTO); {
        }
    }

    @Permission({ProjectOwnerChecker.class, ProjectMemberChecker.class})
    @RequestMapping(value = "/members/{page}/{size}", method = RequestMethod.POST)
    public Page<User> getMembers(@RequestBody ProjectIdDTO projectIdDTO,
                                 @PathVariable(value = "page") int page,
                                 @PathVariable(value = "size") int size) {
        return service.getMembers(projectIdDTO.getProjectId(), page, size);
    }

    @Permission({ProjectOwnerChecker.class})
    @RequestMapping(value = "/members", method = RequestMethod.DELETE)
    public void deleteMember(@RequestBody DeleteProjectMemberDTO deleteProjectMemberDTO) throws NotFoundException {
        service.deleteMember(deleteProjectMemberDTO.getProjectId(), deleteProjectMemberDTO.getUserId());
    }
}
