package by.tasktracker.service;

import by.tasktracker.entity.TaskTag;
import by.tasktracker.repository.TaskTagRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class TaskTagServiceImpl extends NamedServiceImpl<TaskTag, TaskTagRepository> implements TaskTagService {

    @Override
    public TaskTag save(@Valid TaskTag entity) {
        TaskTag taskTag = getByName(entity.getName());
        if (getByName(entity.getName()) != null){
            return taskTag;
        }
        return super.save(entity);
    }
}
