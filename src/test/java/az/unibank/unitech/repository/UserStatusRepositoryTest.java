package az.unibank.unitech.repository;

import az.unibank.unitech.data.entity.UserStatus;
import az.unibank.unitech.data.repository.UserStatusRepositry;
import az.unibank.unitech.enums.UserStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserStatusRepositoryTest {

    @Autowired private UserStatusRepositry userStatusRepositry;

    private final UserStatus status=new UserStatus();

    @BeforeEach
    void setupStatus() {
        status.setId(1L);
        status.setName(UserStatusEnum.CONFIRMED.name());
    }

    @Test
    void givenUserStatus_whenFindUserStatusById_then_ReturnUserStatusObject(){
        UserStatus status1 = userStatusRepositry.save(status);
        UserStatus userStatusById = userStatusRepositry.findUserStatusById(status1.getId());
        assertThat(userStatusById).isNotNull();
    }
}
