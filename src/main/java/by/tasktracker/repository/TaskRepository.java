package by.tasktracker.repository;

import by.tasktracker.entity.Task;
import by.tasktracker.repository.superclass.NamedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskRepository extends NamedRepository<Task> {
    List<Task> findByProjectId(String projectId);
    Page<Task> findByProjectId(String projectId, Pageable pageable);
    List<Task> findByDeveloperId(String developerId);
    Page<Task> findByDeveloperId(String developerId, Pageable pageable);
    List<Task> findByProjectIdAndDeveloperId(String projectId, String developerId);
}
