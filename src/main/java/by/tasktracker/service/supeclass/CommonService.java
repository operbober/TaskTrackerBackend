package by.tasktracker.service.supeclass;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonService<T> {
    T get(String id);
    List<T> getAll();
    Page<T> getAll(int page, int size);
    T save(T entity);
    void delete(String id);

}
