package by.tasktracker.repository;

import by.tasktracker.entity.Project;
import by.tasktracker.repository.superclass.NamedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends NamedRepository<Project> {
    @Query("select p from Project p join p.tasks t where (t.developer.id = :id) group by p")
    List<Project> findByTasksDeveloperId(@Param("id")String id);

    @Query("select p from Project p join p.tasks t where (t.developer.id = :id) group by p")
    Page<Project> findByTasksDeveloperId(@Param("id")String id, Pageable pageable);
}
