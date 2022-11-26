package az.unibank.unitech.data.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static az.unibank.unitech.enums.UserStatusEnum.UNCONFIRMED;

@Data
@Entity
@Table(name = "\"user\"")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String fin;
    @Column(nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private UserStatus status=new UserStatus();
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date createdDate;
    private String activationCode;
    private Date expiredDate;
    private String email;
    @PrePersist
    public void persist() {
        status.setId(UNCONFIRMED.getStatusId());
        setCreatedDate(new Date());
    }
}
