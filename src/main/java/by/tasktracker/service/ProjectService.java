package by.tasktracker.service;

import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.BadConfirmationCodeException;
import by.tasktracker.service.supeclass.NamedService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;

public interface ProjectService extends NamedService<Project> {

    Page<Project> getProjectsByOwner(User owner, int page, int size);
    Page<Project> getProjectsByMember(String userId, int page, int size);
    Project save(String name, String description, User creator);
    Project update(String projectId, String name, String description) throws NotFoundException;
    void sendDeleteCode(String projectId) throws NotFoundException;
    void delete(String projectId, String deleteCode) throws NotFoundException, BadConfirmationCodeException;

    Page<User> getMembers(String projectId, int page, int size);
    void deleteMember(String projectId, String memberId) throws NotFoundException;
}
