package by.tasktracker.controller;

import by.tasktracker.dto.AddTaskDTO;
import by.tasktracker.dto.GetTasksDTO;
import by.tasktracker.entity.Task;
import by.tasktracker.entity.User;
import by.tasktracker.security.Permission;
import by.tasktracker.security.checker.ProjectMemberChecker;
import by.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired private TaskService service;

    @Permission(ProjectMemberChecker.class)
    @RequestMapping(value = "/{page}/{size}", method = RequestMethod.POST)
    public Page<Task> get(@PathVariable("page") int page,
                          @PathVariable("size") int size,
                          @RequestBody @Valid GetTasksDTO tasksDTO){
        return service.getByProjectId(
                tasksDTO.getProjectId(),
                tasksDTO.getName(),
                tasksDTO.getOwner(),
                tasksDTO.getTags(),
                page,
                size
                );
    }

    @Permission(ProjectMemberChecker.class)
    @RequestMapping(method = RequestMethod.POST)
    public Task add(@AuthenticationPrincipal User authorizedUser,
                    @RequestBody @Valid AddTaskDTO taskDTO) {
        return service.save(taskDTO.getName(), taskDTO.getDescription(), authorizedUser, taskDTO.getProjectId());
    }
}
