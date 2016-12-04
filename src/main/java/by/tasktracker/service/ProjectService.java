package by.tasktracker.service;

import by.tasktracker.entity.Project;
import by.tasktracker.entity.ProjectTag;
import by.tasktracker.service.supeclass.NamedService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface ProjectService extends NamedService<Project> {
    List<Project> getByTaskUserId(String userId);
    Page<Project> getByTaskUserId(String userId, int page, int size);
    List<Project> getByTag(String tag);
    Project editTags(String projectId, Set<ProjectTag> tags);
}
