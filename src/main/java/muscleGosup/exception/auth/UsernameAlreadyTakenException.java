package muscleGosup.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Username is already taken")
public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException() {
        super();
    }
}
