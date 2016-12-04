package by.tasktracker.utils;

import by.tasktracker.entity.Role;
import by.tasktracker.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionChecker {
    private static final String NOT_ALLOWED = "You not have permission for this action!";

    private PermissionChecker() {
    }

    public static void checkRoleManagerPermissions (User user, HttpServletResponse response){
        if (!user.getRole().getName().equals(Role.ROLE_MANAGER)) {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, NOT_ALLOWED);
            } catch (IOException ignored) {
                //TODO: IVAN ignore IOException
            }
        }
    }

    public static void checkDeveloperPermissions (User user, User owner, HttpServletResponse response){
        if (!user.getId().equals(owner.getId())) {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, NOT_ALLOWED);
            } catch (IOException ignored) {
                //TODO: IVAN ignore IOException
            }
        }
    }
}
