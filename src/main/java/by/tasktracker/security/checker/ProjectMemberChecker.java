package by.tasktracker.security.checker;

import by.tasktracker.dto.ProjectIdDTO;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.security.PermissionChecker;
import by.tasktracker.service.ProjectService;
import by.tasktracker.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMemberChecker implements PermissionChecker<ProjectIdDTO> {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public boolean checkPermission(User user, ProjectIdDTO projectIdDTO) throws Exception {

        Project project = projectService.get(projectIdDTO.getProjectId());
        if (project == null) {
            throw new NotFoundException("Project not found!");
        }

        project.getMembers().size();
        User userEntity = userService.get(user.getId());
        for (User member : project.getMembers()) {
            if (member.getId().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

}
