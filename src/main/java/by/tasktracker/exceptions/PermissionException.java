package by.tasktracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class PermissionException extends Exception {

    public PermissionException(String message) {
        super(message);
    }
}
