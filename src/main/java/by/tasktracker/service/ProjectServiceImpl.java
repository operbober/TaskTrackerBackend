package by.tasktracker.service;

import by.tasktracker.entity.Project;
import by.tasktracker.repository.ProjectRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends NamedServiceImpl<Project, ProjectRepository> implements ProjectService {
    @Override
    public List<Project> getByTaskUserId(String userId) {
        return repository.findByTasksDeveloperId(userId);
    }

    @Override
    public Page<Project> getByTaskUserId(String userId, int page, int size) {
        return repository.findByTasksDeveloperId(userId, new PageRequest(page, size));
    }
}
