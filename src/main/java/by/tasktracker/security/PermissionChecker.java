package by.tasktracker.security;

import by.tasktracker.entity.User;


public interface PermissionChecker {
    void checkPermission(User user, String jsonBody) throws Exception;
}
