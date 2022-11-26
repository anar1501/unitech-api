package az.unibank.unitech.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class URLConfiguration {
    @Value("${unitech-api.currency-rates-url}")
    private String currencyRatesUrl;

}