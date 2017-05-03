package by.tasktracker.service;

import by.tasktracker.entity.Project;
import by.tasktracker.entity.Task;
import by.tasktracker.entity.User;
import by.tasktracker.repository.TaskRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import static by.tasktracker.spec.TaskSpecification.*;

@Service
public class TaskServiceImpl extends NamedServiceImpl<Task, TaskRepository> implements TaskService {

    @Autowired private ProjectService projectService;
    @Autowired private TaskTagService taskTagService;

    @Override
    public Page<Task> getByProjectId(String projectId, String name, String username, String[] tags, int page, int size) {
        return repository.findAll(Specifications
                                    .where(projectIdLike(projectId))
                                    .and(nameLike(name))
                                    .and(ownerNameLike(username))
                                    .and(tags(tags))
                , new PageRequest(page, size));
    }

    @Override
    public Task save(String name, String description, User user, String projectId) {
        Project project = projectService.get(projectId);
        return save(new Task(name, description, user, project));
    }
}
