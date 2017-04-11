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

    Project save(AddProjectDTO projectDTO, User owner);
    Project update(EditProjectDTO projectDTO) throws NotFoundException;
    void sendDeleteCode(String projectId) throws NotFoundException;
    void delete(DeleteProjectDTO projectDTO) throws NotFoundException, BadConfirmationCodeException;
    Page<Project> getProjectsByOwner(User owner, int page, int size);
    Page<Project> getProjectsByMember(String userId, int page, int size);
    Page<User> getMembers(String projectId, int page, int size);
    void deleteMember(String projectId, String memberId) throws NotFoundException;
}
