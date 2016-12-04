package by.tasktracker.controller;

import by.tasktracker.entity.ProjectTag;
import by.tasktracker.service.ProjectTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects/tags")
public class ProjectTagController {

    @Autowired
    ProjectTagService projectTagService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProjectTag> get(){
        return projectTagService.getAll();
    }
}
