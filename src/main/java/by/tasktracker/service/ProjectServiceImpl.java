package by.tasktracker.service;

import by.tasktracker.dto.AddProjectDTO;
import by.tasktracker.dto.DeleteProjectDTO;
import by.tasktracker.dto.EditProjectDTO;
import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.BadConfirmationCodeException;
import by.tasktracker.repository.ProjectRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import by.tasktracker.utils.MailService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends NamedServiceImpl<Project, ProjectRepository> implements ProjectService {

    @Autowired private MailService mailService;

    @Override
    public Project save(AddProjectDTO projectDTO, User user) {
        return save(new Project(
                projectDTO.getName(),
                projectDTO.getDescription() == null ? null : projectDTO.getDescription().isEmpty() ? null : projectDTO.getDescription(),
                user
        ));
    }

    @Override
    public Project update(EditProjectDTO projectDTO) throws NotFoundException {
        Project project = get(projectDTO.getProjectId());
        if (projectDTO.getName() != null)
            project.setName(projectDTO.getName());
        if (projectDTO.getDescription() != null)
            project.setDescription(projectDTO.getDescription());
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
    public void delete(DeleteProjectDTO projectDTO) throws NotFoundException, BadConfirmationCodeException {
        Project project = get(projectDTO.getProjectId());
        if (projectDTO.getDeleteCode().equals(project.getDeleteCode())) {
            delete(project.getId());
        } else {
            throw new BadConfirmationCodeException();
        }
    }

    @Override
    public Page<Project> getOwnerProjects(User owner, int page, int size) {
        return repository.findByOwner(owner, new PageRequest(page, size));
    }
}
