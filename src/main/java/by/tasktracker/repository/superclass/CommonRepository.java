package by.tasktracker.repository.superclass;

import by.tasktracker.entity.superclass.CommonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<T extends CommonEntity> extends CrudRepository<T, String > {
    Page<T> findAll(Pageable pageable);
}
