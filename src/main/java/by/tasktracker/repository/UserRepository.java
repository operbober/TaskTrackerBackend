package by.tasktracker.repository;

import by.tasktracker.entity.User;
import by.tasktracker.repository.superclass.CommonRepository;

public interface UserRepository extends CommonRepository<User> {
    User findByNameIgnoreCase(String name);
    User findByEmail(String email);
    User findByActivationCode(String activationCode);
}
