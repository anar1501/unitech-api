package az.unibank.unitech.data.repository;


import az.unibank.unitech.data.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepositry extends JpaRepository<UserStatus,Long> {
    UserStatus findUserStatusById(Long id);
}
