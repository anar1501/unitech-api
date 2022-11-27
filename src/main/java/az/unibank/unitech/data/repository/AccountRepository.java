package az.unibank.unitech.data.repository;


import az.unibank.unitech.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAccountsByUserId(Long id);
    Optional<Account> findTopByUserId(Long userId);
}
