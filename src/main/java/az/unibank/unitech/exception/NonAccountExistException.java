package az.unibank.unitech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Such account not founded!")
public class NonAccountExistException extends RuntimeException{
    public NonAccountExistException() {
        super("Such account not founded!");
    }

}

