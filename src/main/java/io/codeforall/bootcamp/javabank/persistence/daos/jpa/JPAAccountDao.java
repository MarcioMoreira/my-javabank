package io.codeforall.bootcamp.javabank.persistence.daos.jpa;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.daos.AccountDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPAAccountDao implements AccountDao {


    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Account> findAll() {

        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Account> query = em.createQuery("SELECT a FROM AbstractModel a" , Account.class);
            return query.getResultList();
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    @Override
    public Account findById(Integer id) {

        Account account = null;
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Account> query = em.createQuery("SELECT a FROM AbstractModel a WHERE a.id = :id" , Account.class);
            query.setParameter("id",id);
        }finally {
            if (em != null){
                em.close();
            }
        }

        return account;
    }

    @Override
    public Account saveOrUpdate(Account modelObject) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Account saved;

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
            Account ac = em.find(Account.class, id);

            if (ac != null) {
                em.remove(ac);      // DELETE FROM account WHERE id = ?
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
