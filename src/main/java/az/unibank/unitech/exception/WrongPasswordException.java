package az.unibank.unitech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Password incorrect")
public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException() {
        super("Password incorrect");
    }

}
