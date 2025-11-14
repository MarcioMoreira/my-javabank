package io.codeforall.bootcamp.javabank.persistence.daos.jpa;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPACustomerDao implements CustomerDao {

    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Integer> getCustomerIds() {

        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Integer> query = em.createQuery("SELECT c.id FROM Customer c" , Integer.class);
            return query.getResultList();
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    @Override
    public List<Customer> findAll() {

        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c" , Customer.class);
            return query.getResultList();
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    @Override
    public Customer findById(Integer id) {

        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.id = :id" , Customer.class);
            try {
                return query.setParameter("id", id).getSingleResult();

            } catch (NoResultException e) {
                return null;
            }
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    @Override
    public Customer saveOrUpdate(Customer modelObject) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Customer saved;

            if (modelObject.getId() == null) {
                em.persist(modelObject);
                saved = modelObject;
            } else {
                saved = em.merge(modelObject);
            }
            em.getTransaction().commit();

            return saved;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Integer id) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Find the entity to delete
            Customer customer = em.find(Customer.class, id);

            if (customer != null) {
                em.remove(customer);      // DELETE FROM customer WHERE id = ?
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
        }
    }
}
