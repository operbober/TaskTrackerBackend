package by.tasktracker.service;

import by.tasktracker.entity.TaskTag;
import by.tasktracker.service.supeclass.NamedService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;

public interface TaskTagService extends NamedService<TaskTag>{

    Page<TaskTag> getByProjectId(String projectId, int page, int size);
    TaskTag save(String name, String projectId);
    void delete(String name, String projectId) throws NotFoundException;
}
