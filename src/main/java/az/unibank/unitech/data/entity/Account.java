package az.unibank.unitech.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance = new BigDecimal(0.0);
    private String price;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user=new User();
}
