package by.tasktracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserNowMemberException extends Exception{

    private static final String  EXCEPTION_MESSAGE = "Failed send invite, user now is member of this project!";

    public UserNowMemberException() {
        super(EXCEPTION_MESSAGE);
    }

}
