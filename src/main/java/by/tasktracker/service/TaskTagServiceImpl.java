package by.tasktracker.service;

import by.tasktracker.entity.Project;
import by.tasktracker.entity.TaskTag;
import by.tasktracker.repository.TaskTagRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import static by.tasktracker.spec.TaskTagSpecification.nameEq;
import static by.tasktracker.spec.TaskTagSpecification.nameLike;
import static by.tasktracker.spec.TaskTagSpecification.projectIdLike;

@Service
public class TaskTagServiceImpl extends NamedServiceImpl<TaskTag, TaskTagRepository> implements TaskTagService {

    @Autowired private ProjectService projectService;

    @Override
    public Page<TaskTag> findAll(String projectId, String name, int page, int size) {
        return repository.findAll(Specifications
                .where(projectIdLike(projectId))
                .and(nameLike(name)),
                new PageRequest(page, size));
    }

    @Override
    public TaskTag save(String name, String projectId) {
        TaskTag tag = getByName(name);
        if (tag != null){
            return tag;
        }
        Project project = projectService.get(projectId);
        return super.save(new TaskTag(name, project));
    }

    @Override
    public void  delete(String name, String projectId) throws NotFoundException {
        TaskTag tag = repository.findOne(Specifications.where(projectIdLike(projectId)).and(nameEq(name)));
        if(tag != null) {
            delete(tag.getId());
        } else {
            throw new NotFoundException("Tag \"" + name + "\" not found!");
        }
    }


}
