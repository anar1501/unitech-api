package az.unibank.unitech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Such pin already registered !")
public class FinAlreadyTaken extends RuntimeException{
    public FinAlreadyTaken() {
        super("Such pin already registered !");
    }
}
