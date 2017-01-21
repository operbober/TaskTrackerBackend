package by.tasktracker.service;

import by.tasktracker.dto.UserDTO;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.UserForActivationNotFoundException;
import by.tasktracker.service.supeclass.CommonService;

public interface UserService extends CommonService<User> {
    User getByName(String name);
    User getByEmail(String email);
    User getByActivationCode(String activationCode);
    User registerNewUser(UserDTO userDTO);
    User activateUser(String activateCode) throws UserForActivationNotFoundException;
}
