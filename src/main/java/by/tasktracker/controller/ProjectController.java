package by.tasktracker.controller;

import by.tasktracker.entity.Project;
import by.tasktracker.entity.User;
import by.tasktracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService service;

    @RequestMapping(method = RequestMethod.GET)
    protected List<Project> get() {
        return service.getAll();
    }

    @RequestMapping(value = "/{page}/{size}",method = RequestMethod.GET)
    protected Page<Project> get(@PathVariable("page") int page,
                                @PathVariable("size") int size)
    {
        return service.getAll(page, size);
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    protected List<Project> get(@AuthenticationPrincipal User activeUser) {
        return service.getByTaskUserId(activeUser.getId());
    }

    @RequestMapping(value = "/my/{page}/{size}", method = RequestMethod.GET)
    protected Page<Project> get(@AuthenticationPrincipal User activeUser,
                                @PathVariable("page") int page,
                                @PathVariable("size") int size) {
        return service.getByTaskUserId(activeUser.getId(), page, size);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project add(@AuthenticationPrincipal User activeUser,
                       @RequestBody Project project){
        project.setCreator(activeUser);
        return service.save(project);
    };
}
