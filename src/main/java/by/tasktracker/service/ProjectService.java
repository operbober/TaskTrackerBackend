package by.tasktracker.service;

import by.tasktracker.entity.Project;
import by.tasktracker.service.supeclass.NamedService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService extends NamedService<Project> {
    List<Project> getByTaskUserId(String userId);
    Page<Project> getByTaskUserId(String userId, int page, int size);
}
