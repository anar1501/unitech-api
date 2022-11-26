package az.unibank.unitech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Can't uploaded money the same account!")
public class SameAccountException extends RuntimeException{
    public SameAccountException() {
        super("Can't uploaded money the same account!");
    }
}
