package by.tasktracker.controller;

import by.tasktracker.dto.ProjectIdDTO;
import by.tasktracker.dto.TaskTagDTO;
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
                             @Valid @RequestBody ProjectIdDTO projectIdDTO){
        return service.getByProjectId(projectIdDTO.getProjectId(), page, size);
    }

    @Permission(ProjectMemberChecker.class)
    @RequestMapping(method = RequestMethod.POST)
    public TaskTag add(@Valid @RequestBody TaskTagDTO taskTagDTO) {
        return service.save(taskTagDTO.getName(), taskTagDTO.getProjectId());
    }

    @Permission(ProjectMemberChecker.class)
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@Valid @RequestBody TaskTagDTO taskTagDTO) throws NotFoundException {
        service.delete(taskTagDTO.getName(), taskTagDTO.getProjectId());
    }
}
