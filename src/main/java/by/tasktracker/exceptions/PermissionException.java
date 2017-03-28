package by.tasktracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class PermissionException extends Exception {

    private static final String  EXCEPTION_MESSAGE = "Permission denied!";

    public PermissionException() {
        super(EXCEPTION_MESSAGE);
    }
}
