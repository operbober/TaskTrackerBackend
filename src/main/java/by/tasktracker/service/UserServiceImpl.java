package by.tasktracker.service;

import by.tasktracker.dto.UserDTO;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.UserForActivationNotFoundException;
import by.tasktracker.repository.UserRepository;
import by.tasktracker.service.supeclass.CommonServiceImpl;
import by.tasktracker.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public User registerNewUser(UserDTO userDTO) {
            User newUser = save(new User(
                    userDTO.getName(),
                    userDTO.getEmail(),
                    passwordEncoder.encode(userDTO.getPassword())
            ));
            mailService.sendEmail(
                    newUser.getEmail(),
                    "Activate account",
                    String.format("http://localhost:3000/api/users/%s", newUser.getActivationCode()));
            return newUser;
    }


    @Override
    public User activateUser(String activateCode) throws UserForActivationNotFoundException {
        User user = getByActivationCode(activateCode);
        if (user == null) {
            throw new UserForActivationNotFoundException();
        }
        user.setActivationCodeNull();
        return save(user);
    }
}
