package az.unibank.unitech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Pin not found")
public class FinNotFoundException extends RuntimeException{
    public FinNotFoundException() {
        super("Pin not found");
    }


}