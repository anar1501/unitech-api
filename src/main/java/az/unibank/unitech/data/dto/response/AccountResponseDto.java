package az.unibank.unitech.data.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponseDto {
    private Long id;
    private BigDecimal balance;
    private String price;
}
