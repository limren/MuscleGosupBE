package muscleGosup.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Email is already taken")
public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException() {
        super();
    }
}
