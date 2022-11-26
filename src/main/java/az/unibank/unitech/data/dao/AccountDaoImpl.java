package az.unibank.unitech.data.dao;

import az.unibank.unitech.data.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@Transactional
@Repository("AccountCardsDao")
public class AccountDaoImpl {
    @PersistenceContext
    EntityManager em;

    public void update(Long id, Account account) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate<Account> update = cb.createCriteriaUpdate(Account.class);
        Root<Account> e = update.from(Account.class);
        update.set("balance", account.getBalance());
        update.where(cb.greaterThanOrEqualTo(e.get("id"), id));
        this.em.createQuery(update).executeUpdate();
    }
}
