package by.tasktracker.security;

import by.tasktracker.entity.User;


public interface PermissionChecker<T> {

    /**
     * @param user authenticated user
     * @param object object of T type from request body
     * @return true if user passed verification then return true, else false
     */
    boolean checkPermission(User user, T object) throws Exception;
}
