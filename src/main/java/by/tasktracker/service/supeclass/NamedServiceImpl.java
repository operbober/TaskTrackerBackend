package by.tasktracker.service.supeclass;



import by.tasktracker.entity.superclass.NamedEntity;
import by.tasktracker.repository.superclass.NamedRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NamedServiceImpl<T extends NamedEntity, R extends NamedRepository<T>> extends CommonServiceImpl<T, R> implements NamedService<T>{

    @Autowired
    protected R repository;

    @Override
    public T getByName(String name) {
        return repository.findByName(name);
    }
}
