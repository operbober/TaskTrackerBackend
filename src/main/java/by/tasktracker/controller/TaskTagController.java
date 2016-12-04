package by.tasktracker.controller;

import by.tasktracker.entity.TaskTag;
import by.tasktracker.service.TaskTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/tags")
public class TaskTagController {

    @Autowired
    TaskTagService taskTagService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskTag> get(){
        return taskTagService.getAll();
    }
}
