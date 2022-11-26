package az.unibank.unitech.resource;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ErrorResponse implements Serializable{
    private String message;
    private String code;

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }
}