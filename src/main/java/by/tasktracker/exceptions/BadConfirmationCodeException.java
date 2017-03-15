package by.tasktracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception if confirmation code form user don't equals with generated code.
 *
 * Created by malets on 2/9/2017.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadConfirmationCodeException extends Exception{

    private static final String  EXCEPTION_MESSAGE = "Bad Confirmation code!";

    public BadConfirmationCodeException() {
        super(EXCEPTION_MESSAGE);
    }
}
