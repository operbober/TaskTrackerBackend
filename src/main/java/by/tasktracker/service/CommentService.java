package by.tasktracker.service;

import by.tasktracker.entity.Comment;
import by.tasktracker.service.supeclass.CommonService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService extends CommonService<Comment> {
    List<Comment> getByTaskId(String taskId);
    Page<Comment> getByTaskId(String  taskId, int page, int size);
}
