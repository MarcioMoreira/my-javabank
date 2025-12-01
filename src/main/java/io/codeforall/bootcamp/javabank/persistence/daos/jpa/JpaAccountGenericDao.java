package io.codeforall.bootcamp.javabank.persistence.daos.jpa;

import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link AccountDao} implementation
 */
@Repository
public class JpaAccountGenericDao extends JpaGenericDao<Account> implements AccountDao {

    /**
     * @see JpaGenericDao#JpaGenericDao(Class)
     */
    public JpaAccountGenericDao() {
        super(Account.class);
    }
}
