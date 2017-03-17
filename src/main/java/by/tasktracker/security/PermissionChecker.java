package by.tasktracker.security;

import by.tasktracker.entity.User;


public interface PermissionChecker<T> {
    void checkPermission(User user, T object) throws Exception;
}
