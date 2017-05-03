package by.tasktracker.repository;

import by.tasktracker.entity.Task;
import by.tasktracker.repository.superclass.NamedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends NamedRepository<Task>, JpaSpecificationExecutor<Task> {

    Page<Task> findByProjectId(String projectId, Specification<Task> specification, Pageable pageable);

/*    @Query("select task from Task task join task.tags tag  where (tag.name like :tag) group by task")
    List<Task> findByTag(@Param("tag")String tag);*/
}
