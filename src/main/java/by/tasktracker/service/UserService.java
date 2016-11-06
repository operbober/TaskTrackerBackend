package by.tasktracker.service;

import by.tasktracker.entity.User;
import by.tasktracker.service.supeclass.CommonService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends CommonService<User> {
    User saveDeveloper(User user);
    User getByUsername(String username);
    List<User> getByRoleName(String roleName);
    Page<User> getByRoleName(String roleName, int page, int size);
}
