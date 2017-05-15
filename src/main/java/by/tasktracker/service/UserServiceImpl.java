package by.tasktracker.service;

import by.tasktracker.entity.User;
import by.tasktracker.exceptions.BadConfirmationCodeException;
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
import java.util.UUID;

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
    public Page<User> getByProject(String projectId, int page, int size) {
        return repository.findByProjectsId(projectId, new PageRequest(page, size));
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
    public User sendEditCode(String id) {
        User user = get(id);
        user.setEditCode(UUID.randomUUID().toString());
        mailService.sendEmail(
                user.getEmail(),
                "User Edit Code",
                user.getEditCode()
        );
        return save(user);
    }

    @Override
    public User edit(String id, String editCode, String email, String password) throws BadConfirmationCodeException {
        User user = get(id);
        if (editCode.equals(user.getEditCode())) {
            if (Objects.nonNull(email)) {
                user.setEmail(email);
            }
            if (Objects.nonNull(password)) {
                user.setPassword(passwordEncoder.encode(password));
            }
            user.setEditCode(null);
            return save(user);
        } else {
            throw new BadConfirmationCodeException();
        }
    }
}
