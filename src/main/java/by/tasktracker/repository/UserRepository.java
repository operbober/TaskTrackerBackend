package by.tasktracker.repository;

import by.tasktracker.entity.User;
import by.tasktracker.repository.superclass.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepository extends CommonRepository<User> {
    User findByUsernameIgnoreCase(String username);
    List<User> findByRoleNameIgnoreCase(String roleName);
    Page<User> findByRoleNameIgnoreCase(String roleName, Pageable pageable);
}
