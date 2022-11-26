package az.unibank.unitech.data.dto.request;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class LoginRequestDto implements Serializable {
    @Pattern(regexp = "^[a-zA-Z_.-]*$", message = "INVALID_FIN")
    private String fin;
    private String password;
}
