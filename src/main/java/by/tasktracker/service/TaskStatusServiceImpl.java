package by.tasktracker.service;

import by.tasktracker.entity.TaskStatus;
import by.tasktracker.repository.TaskStatusRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TaskStatusServiceImpl extends NamedServiceImpl<TaskStatus, TaskStatusRepository> implements TaskStatusService{
}
