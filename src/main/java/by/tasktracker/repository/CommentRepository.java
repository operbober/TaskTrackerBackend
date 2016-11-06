package by.tasktracker.repository;

import by.tasktracker.entity.Comment;
import by.tasktracker.repository.superclass.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentRepository extends CommonRepository<Comment> {
    List<Comment> findByTaskId(String taskId);
    Page<Comment> findByTaskId(String  taskId, Pageable pageable);
}
