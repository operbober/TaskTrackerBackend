package by.tasktracker.service;

import by.tasktracker.entity.Task;
import by.tasktracker.entity.User;
import by.tasktracker.service.supeclass.NamedService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService extends NamedService<Task> {
    List<Task> getByProjectIdAndDeveloperId(String projectId, String developerId);
    Page<Task> getByProjectId(String projectId, int page, int size);
    Page<Task> getByDeveloperId(String developerId, int page, int size);
    Task setDeveloper(String taskId, User user);
    Task switchStatus(String taskId);
    Task setStatus(String taskId, String statusId);
}
