package by.tasktracker.repository;

import by.tasktracker.entity.TaskTag;
import by.tasktracker.repository.superclass.NamedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskTagRepository extends NamedRepository<TaskTag> {
    Page<TaskTag> findByProjectId(String projectId, Pageable pageable);
    TaskTag findByNameAndProjectId(String name, String projectId);
}
