package by.tasktracker.service;

import by.tasktracker.entity.Project;
import by.tasktracker.entity.ProjectTag;
import by.tasktracker.repository.ProjectRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends NamedServiceImpl<Project, ProjectRepository> implements ProjectService {
    
    private ProjectService projectService = this;
    @Autowired
    private ProjectTagService projectTagService;
    
    @Override
    public List<Project> getByTaskUserId(String userId) {
        return repository.findByTasksDeveloperId(userId);
    }

    @Override
    public Page<Project> getByTaskUserId(String userId, int page, int size) {
        return repository.findByTasksDeveloperId(userId, new PageRequest(page, size));
    }

    @Override
    public List<Project> getByTag(String tag) {
        return repository.findByTag(tag);
    }

    @Override
    public Project editTags(String projectId, Set<ProjectTag> tags) {
        Project project = projectService.get(projectId);
        if (project.getTags() == null){
            project.setTags(new HashSet<>());
        }

        Set<ProjectTag> newTags = project.getTags();
        tags.stream().filter(tag -> !newTags.contains(tag)).forEach(tag -> {
            projectTagService.save(tag);
            newTags.add(tag);
        });

        Set<ProjectTag> tagsForRemove = project.getTags().stream().filter(oldTag -> !tags.contains(oldTag)).collect(Collectors.toSet());
        newTags.removeAll(tagsForRemove);

        project.setTags(newTags);
        return projectService.save(project);
    }
}
