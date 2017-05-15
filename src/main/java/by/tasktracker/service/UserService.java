package by.tasktracker.service;

import by.tasktracker.entity.User;
import by.tasktracker.exceptions.BadConfirmationCodeException;
import by.tasktracker.service.supeclass.CommonService;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;

public interface UserService extends CommonService<User> {
    User getByName(String name);
    User getByEmail(String email);
    User getByActivationCode(String activationCode);
    Page<User> getByProject(String projectId, int page, int size);
    User registerNewUser(String name, String email, String password);
    User activateUser(String activateCode) throws NotFoundException;
    User sendEditCode(String userId);
    User edit(String id, String editCode, String email, String password) throws BadConfirmationCodeException;
}
