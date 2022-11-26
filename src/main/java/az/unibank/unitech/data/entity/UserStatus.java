package az.unibank.unitech.data.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static az.unibank.unitech.enums.UserStatusEnum.UNCONFIRMED;

@Data
@Entity
public class UserStatus {
    @Id
    private Long id;
    private String name;
}