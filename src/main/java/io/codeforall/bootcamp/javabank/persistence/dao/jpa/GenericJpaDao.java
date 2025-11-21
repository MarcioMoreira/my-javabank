package io.codeforall.bootcamp.javabank.persistence.dao.jpa;

import io.codeforall.bootcamp.javabank.persistence.model.Model;
import io.codeforall.bootcamp.javabank.persistence.dao.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * A generic jpa data access object to be used as a base for concrete jpa service implementations
 *
 * @param <T> the model type
 * @see Dao
 */
public abstract class GenericJpaDao<T extends Model> implements Dao<T> {

    protected Class<T> modelType;
    protected EntityManager entityManager;

    /**
     * Initializes a new JPA DAO instance given a model type
     *
     * @param modelType the model type
     */
    public GenericJpaDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @see Dao#findAll()
     */
    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(modelType);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * @see Dao#findById(Integer)
     */
    @Override
    public T findById(Integer id) {
        return entityManager.find(modelType, id);
    }

    /**
     * @see Dao#saveOrUpdate(Model)
     */
    @Override
    public T saveOrUpdate(T modelObject) {
        return entityManager.merge(modelObject);
    }

    /**
     * @see Dao#delete(Integer)
     */
    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(modelType, id));
    }
}
