package by.tasktracker.repository;

import by.tasktracker.entity.User;
import by.tasktracker.repository.superclass.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository extends CommonRepository<User> {
    User findByNameIgnoreCase(String name);
    User findByEmail(String email);
    User findByActivationCode(String activationCode);
    Page<User> findByProjectsId(String projectId, Pageable pageable);
}
