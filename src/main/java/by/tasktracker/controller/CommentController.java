package by.tasktracker.controller;

import by.tasktracker.entity.Comment;
import by.tasktracker.entity.User;
import by.tasktracker.repository.UserRepository;
import by.tasktracker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController  {

    private final String NOT_ALLOWED = "You not have permission for this action!";

    @Autowired
    CommentService service;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "{taskId}", method = RequestMethod.GET)
    public List<Comment> getByTask(@PathVariable("taskId") String taskId){
        return service.getByTaskId(taskId);
    };

    @RequestMapping(value = "{taskId}/{page}/{size}", method = RequestMethod.GET)
    public Page<Comment> getByTask(@PathVariable("taskId") String taskId,
                                   @PathVariable("page") int page,
                                   @PathVariable("size") int size){
        return service.getByTaskId(taskId, page, size);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Comment add(@AuthenticationPrincipal User activeUser,
                       @RequestBody Comment comment){
        comment.setCreator(activeUser);
        return service.save(comment);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Comment edit(@AuthenticationPrincipal User activeUser,
                        @RequestBody Comment comment,
                        HttpServletResponse response) throws IOException {
        Comment oldComment = service.get(comment.getId());
        if (oldComment.getCreator().getId().equals(activeUser.getId())) {
            oldComment.setText(comment.getText());
            return service.save(oldComment);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, NOT_ALLOWED);
            return oldComment;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@AuthenticationPrincipal User activeUser,
                       @RequestBody String id,
                       HttpServletResponse response) throws IOException {
        Comment oldComment = service.get(id);
        if (oldComment.getCreator().getId().equals(activeUser.getId())) {
            service.delete(id);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, NOT_ALLOWED);
        }
    }
}
