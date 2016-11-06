package by.tasktracker.service;

import by.tasktracker.entity.Task;
import by.tasktracker.entity.User;
import by.tasktracker.repository.TaskRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl extends NamedServiceImpl<Task, TaskRepository> implements TaskService {

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
        return repository.save(task);
    }
}
