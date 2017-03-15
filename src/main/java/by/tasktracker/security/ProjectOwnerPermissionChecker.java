package by.tasktracker.security;


import by.tasktracker.dto.ProjectIdDTO;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.PermissionException;
import by.tasktracker.service.ProjectService;
import com.google.gson.Gson;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectOwnerPermissionChecker implements PermissionChecker {

    @Autowired
    private ProjectService projectService;

    @Override
    public void checkPermission(User user, String jsonBody) throws PermissionException, NotFoundException {
        Gson gson = new Gson();
        ProjectIdDTO projectIdDTO =  gson.fromJson(jsonBody, ProjectIdDTO.class);
        Project project = projectService.get(projectIdDTO.getProjectId());
        if(project == null) {
            throw new NotFoundException("Project not found");
        }
        if(!project.getOwner().getId().equals(user.getId())) {
            throw new PermissionException("Permission denied!");
        }
    }
}
