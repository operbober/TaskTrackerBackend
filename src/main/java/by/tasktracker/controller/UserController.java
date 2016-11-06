package by.tasktracker.controller;

import by.tasktracker.entity.Role;
import by.tasktracker.entity.User;
import by.tasktracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final String NOT_ALLOWED = "You not have permission for this action!";

    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> get(@RequestParam(value = "roleName", required = false) String roleName){
        return roleName != null ? service.getByRoleName(roleName) : service.getAll();
    }

    @RequestMapping(value = "/{page}/{size}",method = RequestMethod.GET)
    public Page<User> get(@PathVariable("page") int page,
                          @PathVariable("size") int size,
                          @RequestParam(value = "roleName", required = false) String roleName){
        return roleName != null ? service.getByRoleName(roleName, page, size) : service.getAll(page, size);
    }

    @RequestMapping(value = "/me",method = RequestMethod.GET)
    public User getMe(@AuthenticationPrincipal User user){
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User addOrUpdate(@RequestBody User user,
                            HttpServletResponse response) throws IOException {
        User oldUser = user.getId() != null ? service.get(user.getId()) : null;
        if (oldUser == null || oldUser.getRole().getName().equals(Role.ROLE_DEVELOPER)) {
            return service.saveDeveloper(user);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, NOT_ALLOWED);
            return oldUser;
        }
    }

    @RequestMapping(value = "/{developerId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "developerId") String id,
                       HttpServletResponse response) throws IOException {
        User oldUser = service.get(id);
        if (oldUser.getRole().getName().equals(Role.ROLE_DEVELOPER)) {
            service.delete(id);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, NOT_ALLOWED);
        }
    }
}
