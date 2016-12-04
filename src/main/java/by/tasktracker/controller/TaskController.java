package by.tasktracker.controller;

import by.tasktracker.entity.Task;
import by.tasktracker.entity.User;
import by.tasktracker.service.TaskService;
import by.tasktracker.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static by.tasktracker.utils.PermissionChecker.checkDeveloperPermissions;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskService service;

    @Autowired
    TaskStatusService taskStatusService;

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
    public Page<Task> get(@PathVariable("by") String by,
                                   @PathVariable("id") String id,
                                   @PathVariable("page") int page,
                                   @PathVariable("size") int size){
        switch (by) {
            case "project":
                return service.getByProjectId(id, page, size);
            case "developer":
                return service.getByDeveloperId(id, page, size);
            default:
                return new PageImpl<>(new ArrayList<>());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Task add(@AuthenticationPrincipal User activeUser,
                    @RequestBody Task task){
        task.setCreator(activeUser);
        return service.save(task);
    }

    @Deprecated
    @RequestMapping(value = "/switchStatus/{taskId}", method = RequestMethod.PUT)
    public Task switchStatus(@AuthenticationPrincipal User activeUser,
                             @PathVariable("taskId") String  taskId,
                             HttpServletResponse response) {
        Task oldTask = service.get(taskId);
        checkDeveloperPermissions(activeUser, oldTask.getDeveloper(), response);
        return service.switchStatus(taskId);
    }

    @RequestMapping(value = "/setStatus/{taskId}", method = RequestMethod.PUT)
    public Task setStatus(@AuthenticationPrincipal User activeUser,
                          @PathVariable("taskId") String  taskId,
                          @RequestBody String statusId,
                          HttpServletResponse response) {
        Task oldTask = service.get(taskId);
        checkDeveloperPermissions(activeUser, oldTask.getDeveloper(), response);
        return service.setStatus(taskId, statusId);
    }

    @RequestMapping(value = "/selectDeveloper/{taskId}", method = RequestMethod.PUT)
    public Task setDeveloper(@PathVariable("taskId") String  taskId,
                             @RequestBody(required = false) User developer)
    {
        return service.setDeveloper(taskId, developer);
    }
    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public Task addTag(@AuthenticationPrincipal User activeUser,
                       @RequestBody Task task) {
        return service.editTags(task.getId(), task.getTags());
    }

}
