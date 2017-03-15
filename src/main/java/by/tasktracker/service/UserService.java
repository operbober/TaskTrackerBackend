package by.tasktracker.service;

import by.tasktracker.dto.UserDTO;
import by.tasktracker.entity.User;
import by.tasktracker.service.supeclass.CommonService;
import javassist.NotFoundException;

public interface UserService extends CommonService<User> {
    User getByName(String name);
    User getByEmail(String email);
    User getByActivationCode(String activationCode);
    User registerNewUser(UserDTO userDTO);
    User activateUser(String activateCode) throws NotFoundException;
}
