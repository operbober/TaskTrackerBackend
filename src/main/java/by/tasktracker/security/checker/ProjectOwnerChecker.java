package by.tasktracker.security.checker;


import by.tasktracker.dto.ProjectIdDTO;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.PermissionException;
import by.tasktracker.security.PermissionChecker;
import by.tasktracker.service.ProjectService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ProjectOwnerChecker implements PermissionChecker<ProjectIdDTO> {

    @Autowired
    private ProjectService projectService;

    @Override
    public boolean checkPermission(User user, @Valid ProjectIdDTO projectIdDTO) throws PermissionException, NotFoundException {
        Project project = projectService.get(projectIdDTO.getProjectId());
        if(project == null) {
            throw new NotFoundException("Project not found!");
        }

        return project.getOwner().getId().equals(user.getId());
    }
}
