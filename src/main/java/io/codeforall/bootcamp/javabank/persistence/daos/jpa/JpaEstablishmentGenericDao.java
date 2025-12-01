package io.codeforall.bootcamp.javabank.persistence.daos.jpa;

import io.codeforall.bootcamp.javabank.model.Establishment;
import io.codeforall.bootcamp.javabank.persistence.daos.EstablishmentDao;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link EstablishmentDao} implementation
 */
@Repository
public class JpaEstablishmentGenericDao extends JpaGenericDao<Establishment> implements EstablishmentDao {

    /**
     * @see JpaGenericDao#JpaGenericDao(Class)
     */
    public JpaEstablishmentGenericDao() {
        super(Establishment.class);
    }
}
