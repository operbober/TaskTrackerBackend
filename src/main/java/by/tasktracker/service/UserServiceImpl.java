package by.tasktracker.service;

import by.tasktracker.entity.User;
import by.tasktracker.repository.UserRepository;
import by.tasktracker.service.supeclass.CommonServiceImpl;
import by.tasktracker.utils.MailService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends CommonServiceImpl<User, UserRepository> implements UserService {

    @Autowired private MailService mailService;

    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public User getByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User getByActivationCode(String activationCode) {
        return repository.findByActivationCode(activationCode);
    }

    @Override
    public User registerNewUser(String name, String email, String password) {
            User newUser = save(new User(
                    name,
                    email,
                    passwordEncoder.encode(password)
            ));
            mailService.sendEmail(
                    newUser.getEmail(),
                    "Activate account",
                    String.format("http://localhost:3000/api/users/%s", newUser.getActivationCode()));
            return newUser;
    }


    @Override
    public User activateUser(String activateCode) throws NotFoundException {
        User user = getByActivationCode(activateCode);
        if (Objects.isNull(user)) {
            throw new NotFoundException("Bad activation link!");
        }
        user.setActivationCodeNull();
        return save(user);
    }

    @Override
    public Page<User> getByProject(String projectId, int page, int size) {
        return repository.findByProjectsId(projectId, new PageRequest(page, size));
    }
}
