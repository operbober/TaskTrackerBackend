package by.tasktracker.service;

import by.tasktracker.entity.Comment;
import by.tasktracker.repository.CommentRepository;
import by.tasktracker.service.supeclass.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends CommonServiceImpl<Comment, CommentRepository> implements CommentService {

    @Autowired
    UserService userService;

    @Override
    public List<Comment> getByTaskId(String  taskId) {
        return repository.findByTaskId(taskId);
    }

    @Override
    public Page<Comment> getByTaskId(String  taskId, int page, int size) {
        return  repository.findByTaskId(taskId, new PageRequest(page, size));
    }
}
