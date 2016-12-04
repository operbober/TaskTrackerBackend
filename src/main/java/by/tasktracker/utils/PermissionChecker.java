package by.tasktracker.utils;

import by.tasktracker.entity.User;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MaletsI on 04.12.2016.
 */
public class PermissionChecker {
    private static final String NOT_ALLOWED = "You not have permission for this action!";

    private PermissionChecker() {
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
