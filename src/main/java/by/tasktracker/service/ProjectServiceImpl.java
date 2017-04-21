package by.tasktracker.service;

import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.BadConfirmationCodeException;
import by.tasktracker.repository.ProjectRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import by.tasktracker.utils.MailService;
import javassist.NotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class ProjectServiceImpl extends NamedServiceImpl<Project, ProjectRepository> implements ProjectService {

    @Autowired private UserService userService;

    @Autowired private MailService mailService;

    @Override
    public Project save(String name, String description, User creator) {
        Project project = new Project(
                name,
                Objects.nonNull(description)
                        ? description.isEmpty()
                            ? null
                            : description
                        : null,
                creator
        );
        project.setMembers(new HashSet<>(Collections.singletonList(creator)));
        return save(project);
    }

    @Override
    public Project update(String projectId, String name, String description) throws NotFoundException {
        Project project = get(projectId);
        if (Objects.nonNull(name))
            project.setName(name);
        if (Objects.nonNull(description))
            project.setDescription(description);
        return save(project);
    }

    @Override
    public void sendDeleteCode(String projectId) throws NotFoundException {
        Project project = get(projectId);
        project.generateDeleteCode();
        save(project);
        mailService.sendEmail(
                project.getOwner().getEmail(),
                "Delete project " + project.getName() + "code",
                project.getDeleteCode()
        );
    }

    @Override
    public void delete(String projectId, String deleteCode) throws NotFoundException, BadConfirmationCodeException {
        Project project = get(projectId);
        if (deleteCode.equals(project.getDeleteCode())) {
            delete(project.getId());
        } else {
            throw new BadConfirmationCodeException();
        }
    }

    @Override
    public Page<Project> getProjectsByOwner(User owner, int page, int size) {
        return repository.findByOwner(owner, new PageRequest(page, size));
    }

    @Override
    public Page<Project> getProjectsByMember(String userId, int page, int size) {
        return repository.findByMembersId(userId, new PageRequest(page, size));
    }

    @Override
    public Page<User> getMembers(String projectId, int page, int size) {
        return userService.getByProject(projectId, page, size);
    }

    @Override
    public void deleteMember(String projectId, String memberId) throws NotFoundException {
        Project project = get(projectId);
        if (project.getOwner().getId().equals(memberId)) return;

        Hibernate.initialize(project.getMembers());
        Set<User> members = project.getMembers();
        for (User member : members) {
            if (member.getId().equals(memberId)) {
                members.remove(member);
                project.setMembers(members);
                save(project);
                return;
            }
        }

        throw new NotFoundException("Member not found");
    }
}
