package io.codeforall.bootcamp.javabank.persistence.daos.jpa;

import io.codeforall.bootcamp.javabank.model.transaction.Transaction;
import io.codeforall.bootcamp.javabank.persistence.daos.TransactionDao;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link TransactionDao} implementation
 */
@Repository
public class JpaTransactionGenericDao extends JpaGenericDao<Transaction> implements TransactionDao {

    /**
     * @see JpaGenericDao#JpaGenericDao(Class)
     */
    public JpaTransactionGenericDao() {
        super(Transaction.class);
    }
}
