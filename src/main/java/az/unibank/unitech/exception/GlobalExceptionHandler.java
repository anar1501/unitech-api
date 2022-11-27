package az.unibank.unitech.exception;

import az.unibank.unitech.resource.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyConfirmedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleAlreadyConfirmedException(AlreadyConfirmedException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),"400");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnconfirmedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleUnconfirmedException(UnconfirmedException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),"400");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FinNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(FinNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),"404");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleWrongPasswordException(WrongPasswordException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),"400");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FinAlreadyTaken.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleFinAlreadyTaken(FinAlreadyTaken customerNotFoundException) {
        ErrorResponse ex = new ErrorResponse(customerNotFoundException.getMessage(), "400");
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        ErrorResponse ex = new ErrorResponse(userNotFoundException.getMessage(), "404");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SameAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleSameAccountException(SameAccountException userNotFoundException) {
        ErrorResponse ex = new ErrorResponse(userNotFoundException.getMessage(), "400");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NonAccountExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNonAccountExistException(NonAccountExistException nonAccountExistException) {
        ErrorResponse ex = new ErrorResponse(nonAccountExistException.getMessage(), "404");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoEnoughCashStrangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNoEnoughCashStrangeException(NoEnoughCashStrangeException noEnoughCashStrangeException) {
        ErrorResponse ex = new ErrorResponse(noEnoughCashStrangeException.getMessage(), "400");
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeactiveAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleDeactiveAccountException(DeactiveAccountException deactiveAccountException) {
        ErrorResponse ex = new ErrorResponse(deactiveAccountException.getMessage(), "400");
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}
