package by.tasktracker.service.supeclass;

import by.tasktracker.entity.superclass.CommonEntity;

public interface NamedService<T extends CommonEntity> extends CommonService<T> {
    T getByName(String name);
}
