package by.tasktracker.service;

import by.tasktracker.entity.Role;
import by.tasktracker.entity.User;
import by.tasktracker.repository.UserRepository;
import by.tasktracker.service.supeclass.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends CommonServiceImpl<User, UserRepository> implements UserService {

    @Autowired
    RoleService roleService;

    @Override
    public User saveDeveloper(User user) {
        Role roleDeveloper = roleService.getByName(Role.ROLE_DEVELOPER);
        user.setRole(roleDeveloper);
        return repository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsernameIgnoreCase(username);
    }

    @Override
    public List<User> getByRoleName(String roleName) {
        return repository.findByRoleNameIgnoreCase(roleName);
    }

    @Override
    public Page<User> getByRoleName(String roleName, int page, int size) {
        return repository.findByRoleNameIgnoreCase(roleName, new PageRequest(page, size));
    }
}
