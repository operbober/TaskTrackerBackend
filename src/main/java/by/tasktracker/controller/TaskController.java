package by.tasktracker.controller;

import by.tasktracker.entity.Task;
import by.tasktracker.entity.User;
import by.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final String NOT_ALLOWED = "You not have permission for this action!";

    @Autowired
    TaskService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Task> get(){
        return service.getAll();
    }

    @RequestMapping(value = "{by}/{id}", method = RequestMethod.GET)
    public List<Task> get(@AuthenticationPrincipal User activeUser,
                          @PathVariable("by") String by,
                          @PathVariable("id") String id){
        switch (by) {
            case "project&developer":
                return service.getByProjectIdAndDeveloperId(id, activeUser.getId());
            case "project":
                return service.getByProjectIdAndDeveloperId(id, null);
            case "developer":
                return service.getByProjectIdAndDeveloperId(null, id);
            default:
                return new ArrayList<>();
        }
    }

    @RequestMapping(value = "{by}/{id}/{page}/{size}", method = RequestMethod.GET)
    public Page<Task> getByProject(@PathVariable("by") String by,
                                   @PathVariable("id") String id,
                                   @PathVariable("page") int page,
                                   @PathVariable("size") int size){
        switch (by) {
            case "project":
                return service.getByProjectId(id, page, size);
            case "developer":
                return service.getByDeveloperId(id, page, size);
            default:
                return new PageImpl<Task>(new ArrayList<>());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Task add(@AuthenticationPrincipal User activeUser,
                    @RequestBody Task task){
        task.setCreator(activeUser);
        return service.save(task);
    }

    @RequestMapping(value = "/switchStatus/{taskId}" ,method = RequestMethod.PUT)
    public Task switchStatus(@AuthenticationPrincipal User activeUser,
                             @PathVariable("taskId") String  taskId,
                             HttpServletResponse response) throws IOException {
        Task oldTask = service.get(taskId);
        if (oldTask.getDeveloper().getUsername().equals(activeUser.getUsername())) {
            return service.switchStatus(taskId);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, NOT_ALLOWED);
            return oldTask;
        }
    }

    @RequestMapping(value = "/selectDeveloper/{taskId}" ,method = RequestMethod.PUT)
    public Task switchStatus(@PathVariable("taskId") String  taskId,
                             @RequestBody(required = false) User developer)
    {
        return service.setDeveloper(taskId, developer);
    }

}
