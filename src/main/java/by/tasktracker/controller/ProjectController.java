package by.tasktracker.controller;

import by.tasktracker.dto.AddProjectDTO;
import by.tasktracker.dto.DeleteProjectDTO;
import by.tasktracker.dto.EditProjectDTO;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.BadConfirmationCodeException;
import by.tasktracker.service.ProjectService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired private ProjectService service;

    @RequestMapping(value = "/my/{page}/{size}", method = RequestMethod.GET)
    public Page<Project> getMyProjects(@AuthenticationPrincipal User authorizedUser,
                                       @PathVariable(value = "page") int page,
                                       @PathVariable(value = "size") int size) {
        return service.getOwnerProjects(authorizedUser, page, size);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project addProject(@RequestBody @Valid AddProjectDTO projectDTO,
                              @AuthenticationPrincipal User authorizedUser) {
        return service.save(projectDTO, authorizedUser);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Project editProject(@RequestBody @Valid EditProjectDTO projectDTO,
                               @AuthenticationPrincipal User authorizedUser,
                               HttpServletResponse response) throws IOException {
        try {
            return service.update(projectDTO, authorizedUser);
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Project not found!");
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteProject(@RequestBody @Valid DeleteProjectDTO projectDTO,
                              @AuthenticationPrincipal User authorizedUser,
                              HttpServletResponse response) throws IOException {
        try {
            if(projectDTO.getDeleteCode() == null) {
                service.sendDeleteCode(projectDTO.getId(), authorizedUser);
            } else {
                service.delete(projectDTO, authorizedUser);
            }
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Project not found!");
        } catch (BadConfirmationCodeException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Confirmation code!");
        }
    }

}
