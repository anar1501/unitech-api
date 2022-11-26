package az.unibank.unitech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No enough cash strange!!")
public class NoEnoughCashStrangeException extends RuntimeException{
    public NoEnoughCashStrangeException() {
        super("No enough cash strange!!");
    }
}
