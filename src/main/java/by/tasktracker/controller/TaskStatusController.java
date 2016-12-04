package by.tasktracker.controller;

import by.tasktracker.entity.Task;
import by.tasktracker.entity.TaskStatus;
import by.tasktracker.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by MaletsI on 04.12.2016.
 */
@RestController
@RequestMapping("/api/taskStatus")
public class TaskStatusController {

    @Autowired
    TaskStatusService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskStatus> get(){
        return service.getAll();
    }
}
