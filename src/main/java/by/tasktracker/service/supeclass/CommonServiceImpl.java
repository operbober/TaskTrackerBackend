package by.tasktracker.service.supeclass;

import by.tasktracker.entity.superclass.CommonEntity;
import by.tasktracker.repository.superclass.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonServiceImpl<T extends CommonEntity, R extends CommonRepository<T>>
        implements CommonService<T> {

    @Autowired
    protected R repository;

    @Override
    public T get(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        repository.findAll().forEach(entities::add);
        return entities;
    }

    @Override
    public Page<T> getAll(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public T save(@Valid T entity) {
        return repository.save(entity);
    }
}
