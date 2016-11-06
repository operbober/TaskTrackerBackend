package by.tasktracker.service;

import by.tasktracker.entity.Role;
import by.tasktracker.repository.RoleRepository;
import by.tasktracker.service.supeclass.NamedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends NamedServiceImpl<Role, RoleRepository> implements RoleService{
}
