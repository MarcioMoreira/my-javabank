package io.codeforall.bootcamp.javabank.persistence.daos.jpa;

import io.codeforall.bootcamp.javabank.model.Recipient;
import io.codeforall.bootcamp.javabank.persistence.daos.RecipientDao;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link RecipientDao} implementation
 */
@Repository
public class JpaRecipientGenericDao extends JpaGenericDao<Recipient> implements RecipientDao {

    /**
     * @see JpaGenericDao#JpaGenericDao(Class)
     */
    public JpaRecipientGenericDao() {
        super(Recipient.class);
    }
}
