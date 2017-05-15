package by.tasktracker.controller;

import by.tasktracker.dto.User.EditUserDTO;
import by.tasktracker.dto.User.UserDTO;
import by.tasktracker.entity.User;
import by.tasktracker.exceptions.BadConfirmationCodeException;
import by.tasktracker.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired private UserService service;

    @RequestMapping(value = "/me",method = RequestMethod.GET)
    public User getMe(@AuthenticationPrincipal User user){
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User add(@RequestBody @Valid UserDTO userDTO){
        return service.registerNewUser(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
    }

    @RequestMapping(value = "/{activationCode}",method = RequestMethod.GET)
    public User activate(@PathVariable("activationCode") String activationCode) throws NotFoundException {
        return service.activateUser(activationCode);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User update(@AuthenticationPrincipal User user,
                       @Valid @RequestBody EditUserDTO userDTO) throws BadConfirmationCodeException {
        if (Objects.isNull(userDTO.getEditCode())) {
            return service.sendEditCode(user.getId());
        } else {
            return service.edit(user.getId(), userDTO.getEditCode(), userDTO.getEmail(), userDTO.getPassword());
        }
    }
}
