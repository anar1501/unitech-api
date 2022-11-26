package az.unibank.unitech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Account is deactive!")
    public class DeactiveAccountException extends RuntimeException{
    public DeactiveAccountException() {
        super("Account is deactive!");
    }

}
