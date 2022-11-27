package az.unibank.unitech.data.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Currency;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class TransferRequestDto {
    private String email;
    @Pattern(regexp = "^[a-zA-Z_.-]*$", message = "INVALID_FIN")
    @NotEmpty
    private String fin;
    private Long accountId;
    private double amount;
    private Long userId;
    private String price;
}
