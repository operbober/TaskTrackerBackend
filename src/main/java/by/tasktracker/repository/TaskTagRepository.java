package by.tasktracker.repository;

import by.tasktracker.entity.TaskTag;
import by.tasktracker.repository.superclass.NamedRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskTagRepository extends NamedRepository<TaskTag>, JpaSpecificationExecutor<TaskTag> {
}
