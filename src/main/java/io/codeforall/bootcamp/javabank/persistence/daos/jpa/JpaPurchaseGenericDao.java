package io.codeforall.bootcamp.javabank.persistence.daos.jpa;

import io.codeforall.bootcamp.javabank.model.transaction.Purchase;
import io.codeforall.bootcamp.javabank.persistence.daos.PurchaseDao;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link PurchaseDao} implementation
 */
@Repository
public class JpaPurchaseGenericDao extends JpaGenericDao<Purchase> implements PurchaseDao {

    /**
     * @see JpaGenericDao#JpaGenericDao(Class)
     */
    public JpaPurchaseGenericDao() {
        super(Purchase.class);
    }
}
