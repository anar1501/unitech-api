package az.unibank.unitech.data.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Currency;

import java.math.BigDecimal;

@Data
public class CreateAccountRequestDto {
    private Long userId;
    private BigDecimal amount;
}
