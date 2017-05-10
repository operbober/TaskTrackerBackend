package by.tasktracker.controller;

import by.tasktracker.dto.AddTaskTagDTO;
import by.tasktracker.dto.GetTaskTagDTO;
import by.tasktracker.entity.TaskTag;
import by.tasktracker.security.Permission;
import by.tasktracker.security.checker.ProjectMemberChecker;
import by.tasktracker.service.TaskTagService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tasks/tags")
public class TaskTagController {

    @Autowired
    private TaskTagService service;

    @Permission(ProjectMemberChecker.class)
    @RequestMapping(value = "/{page}/{size}", method = RequestMethod.POST)
    public Page<TaskTag> get(@PathVariable("page") int page,
                             @PathVariable("size") int size,
                             @Valid @RequestBody GetTaskTagDTO tag){
        return service.findAll(tag.getProjectId(), tag.getName(), page, size);
    }

    @Permission(ProjectMemberChecker.class)
    @RequestMapping(method = RequestMethod.POST)
    public TaskTag add(@Valid @RequestBody AddTaskTagDTO taskTagDTO) {
        return service.save(taskTagDTO.getName(), taskTagDTO.getProjectId());
    }

    @Permission(ProjectMemberChecker.class)
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@Valid @RequestBody AddTaskTagDTO tag) throws NotFoundException {
        service.delete(tag.getName(), tag.getProjectId());
    }
}
