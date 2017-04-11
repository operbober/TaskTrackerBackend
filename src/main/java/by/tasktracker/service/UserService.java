package by.tasktracker.service;

import by.tasktracker.dto.UserDTO;
import by.tasktracker.entity.User;
import by.tasktracker.service.supeclass.CommonService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;

public interface UserService extends CommonService<User> {
    User getByName(String name);
    User getByEmail(String email);
    User getByActivationCode(String activationCode);
    Page<User> getByProject(String projectId, int page, int size);
    User registerNewUser(UserDTO userDTO);
    User activateUser(String activateCode) throws NotFoundException;
}
