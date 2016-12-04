package by.tasktracker.service;

import by.tasktracker.entity.ProjectTag;
import by.tasktracker.repository.ProjectTagRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ProjectTagServiceImpl extends NamedServiceImpl<ProjectTag, ProjectTagRepository> implements ProjectTagService {

    @Override
    public ProjectTag save(@Valid ProjectTag entity) {
        ProjectTag projectTag = getByName(entity.getName());
        if (getByName(entity.getName()) != null){
            return projectTag;
        }
        return super.save(entity);
    }
}
