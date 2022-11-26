package az.unibank.unitech.repository;

import az.unibank.unitech.data.entity.Account;
import az.unibank.unitech.data.entity.User;
import az.unibank.unitech.data.entity.UserStatus;
import az.unibank.unitech.data.repository.AccountRepository;
import az.unibank.unitech.data.repository.UserRepository;
import az.unibank.unitech.data.repository.UserStatusRepositry;
import az.unibank.unitech.enums.UserStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserStatusRepositry userStatusRepositry;

    private final User user=new User();
    private final UserStatus status=new UserStatus();
    private final Account account=new Account();
    private final Account account2=new Account();

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
        status.setId(2L);
        status.setName(UserStatusEnum.CONFIRMED.name());
    }

    @BeforeEach
    void setupAccount() {
        account.setBalance(new BigDecimal(100));
        account.setPrice("AZN");
        account.setUser(user);
        account2.setBalance(new BigDecimal(200));
        account2.setPrice("TL");
        account2.setUser(user);
    }

    @Test
    void givenAccountList_whenGetAll_then_AccountList() {
        userStatusRepositry.save(status);
        userRepository.save(user);
        List<Account> accountList = Arrays.asList(account, account2);
        accountRepository.saveAll(accountList);
        List<Account> newAccountList = accountRepository.findAll();
        assertThat(newAccountList).isNotEmpty();
        assertThat(newAccountList.size()).isEqualTo(2);
    }


    @Test
    void givenAccountList_whenGetAllByUserId_then_ReturnAccountList() {
        userStatusRepositry.save(status);
        User save = userRepository.save(user);
        List<Account> accountList = Arrays.asList(account, account2);
        accountRepository.saveAll(accountList);
        List<Account> newAccountList = accountRepository.findAccountsByUserId(save.getId());
        assertThat(newAccountList).isNotEmpty();
        assertThat(newAccountList.size()).isEqualTo(2);
    }
}
