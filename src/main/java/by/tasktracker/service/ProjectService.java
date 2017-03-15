package by.tasktracker.service;

import by.tasktracker.dto.AddProjectDTO;
import by.tasktracker.dto.DeleteProjectDTO;
import by.tasktracker.dto.EditProjectDTO;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.BadConfirmationCodeException;
import by.tasktracker.service.supeclass.NamedService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;

public interface ProjectService extends NamedService<Project> {

    Page<Project> getOwnerProjects(User owner, int page, int size);
    Project save(AddProjectDTO projectDTO, User owner);
    Project update(EditProjectDTO projectDTO) throws NotFoundException;
    void sendDeleteCode(String projectId) throws NotFoundException;
    void delete(DeleteProjectDTO projectDTO) throws NotFoundException, BadConfirmationCodeException;
}
