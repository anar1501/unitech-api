package az.unibank.unitech.repository;

import az.unibank.unitech.data.entity.User;
import az.unibank.unitech.data.entity.UserStatus;
import az.unibank.unitech.data.repository.UserRepository;
import az.unibank.unitech.enums.UserStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired private UserRepository userRepository;

    private final User user=new User();
    private final UserStatus status=new UserStatus();

    @BeforeEach
    void setupUser() {
        user.setFin("ABC1234");
        user.setEmail("test@gmail.com");
        user.setCreatedDate(new Date());
        user.setStatus(status);
        user.setPassword("test12345");
    }

    @BeforeEach
    void setupStatus() {
        status.setName(UserStatusEnum.CONFIRMED.name());
    }

    @Test
    void testAlreadyRegistredUser() {
        User save = userRepository.save(user);
        assertThat(save).isNotNull();
        assertThat(save.getId()).isGreaterThan(0L);
        assertThat(save.getFin()).isEqualTo("ABC1234");
    }
}
