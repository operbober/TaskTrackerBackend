package by.tasktracker.service;

import by.tasktracker.entity.Task;
import by.tasktracker.entity.User;
import by.tasktracker.service.supeclass.NamedService;
import org.springframework.data.domain.Page;

public interface TaskService extends NamedService<Task> {
    Page<Task> getByProjectId(String projectId, String name, String username, String[] tags, int page, int size);
    Task save(String name, String description, User user, String projectId);
}
