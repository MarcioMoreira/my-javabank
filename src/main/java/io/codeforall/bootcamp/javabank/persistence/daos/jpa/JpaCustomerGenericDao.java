package io.codeforall.bootcamp.javabank.persistence.daos.jpa;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;
import org.springframework.stereotype.Repository;

/**
 * A JPA {@link CustomerDao} implementation
 */
@Repository
public class JpaCustomerGenericDao extends JpaGenericDao<Customer> implements CustomerDao {

    /**
     * @see JpaGenericDao#JpaGenericDao(Class)
     */
    public JpaCustomerGenericDao() {
        super(Customer.class);
    }
}
