package az.unibank.unitech.data.repository;

import az.unibank.unitech.data.entity.Account;
import az.unibank.unitech.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFin(String pin);
    Optional<User> findByEmail(String email);
    Boolean existsByFin(String pin);
    User findByActivationCode(String activationCode);
}
