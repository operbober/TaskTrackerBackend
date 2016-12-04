package by.tasktracker.service;

import by.tasktracker.entity.Task;
import by.tasktracker.entity.TaskStatus;
import by.tasktracker.entity.User;
import by.tasktracker.repository.TaskRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl extends NamedServiceImpl<Task, TaskRepository> implements TaskService {

    private TaskService taskService = this;
    @Autowired
    private TaskStatusService taskStatusService;

    @Override
    public Task save(@Valid Task task) {
        TaskStatus taskStatus = taskStatusService.getByName(TaskStatus.OPEN);
        if (task.getTaskStatus() == null) {
            task.setTaskStatus(taskStatus);
        }
        return super.save(task);
    }

    @Override
    public List<Task> getByProjectIdAndDeveloperId(String projectId, String developerId) {
        List<Task> tasks = new ArrayList<>();
        if (projectId != null && developerId != null){
            tasks =  repository.findByProjectIdAndDeveloperId(projectId, developerId);
        } else if (projectId != null){
            tasks =  repository.findByProjectId(projectId);
        } else if (developerId !=null){
            tasks =  repository.findByDeveloperId(developerId);
        } else {
            repository.findAll().forEach(tasks::add);
        }
        return tasks;
    }

    @Override
    public Page<Task> getByProjectId(String projectId, int page, int size) {
        return repository.findByProjectId(projectId, new PageRequest(page, size));
    }

    @Override
    public Page<Task> getByDeveloperId(String developerId, int page, int size) {
        return repository.findByProjectId(developerId, new PageRequest(page, size));
    }

    @Override
    public Task setDeveloper(String taskId, User developer) {
        Task task = repository.findOne(taskId);
        task.setDeveloper(developer);
        return repository.save(task);
    }

    @Override
    public Task switchStatus(String taskId) {
        Task task = repository.findOne(taskId);
        task.setStatus(!task.isStatus());
        if (task.isStatus()) {
            task.setTaskStatus(taskStatusService.getByName(TaskStatus.CLOSED));
        } else {
            task.setTaskStatus(taskStatusService.getByName(TaskStatus.OPEN));
        }
        return repository.save(task);
    }

    @Override
    public Task setStatus(String taskId, String statusId) {
        Task task = taskService.get(taskId);
        TaskStatus taskStatus = taskStatusService.get(statusId);
        task.setTaskStatus(taskStatus);
        switch (taskStatus.getName()) {
            case TaskStatus.OPEN:
                task.setStatus(false);
                break;
            case TaskStatus.CLOSED:
                task.setStatus(true);
                break;
            default:
                task.setStatus(null);
                break;
        }
        return taskService.save(task);
    }
}
