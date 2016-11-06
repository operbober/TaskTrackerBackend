package by.tasktracker.repository.superclass;

import by.tasktracker.entity.superclass.NamedEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedRepository<T extends NamedEntity> extends CommonRepository<T>{
    T findByName(String name);
}
