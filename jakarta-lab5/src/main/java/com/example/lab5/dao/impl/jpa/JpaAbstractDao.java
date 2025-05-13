package com.example.lab5.dao.impl.jpa;

import com.example.lab5.dao.AbstractDao;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Collection;

@Stateless
public abstract class JpaAbstractDao<T> implements AbstractDao<T> {
    @PersistenceContext
    protected EntityManager em;

    private final Class<T> entityClass;

    JpaAbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public T get(Integer id) {
        return em.find(entityClass, id);
    }

    @Override
    public Collection<T> getAll() {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public void create(T entity) {
        em.persist(entity);
        em.flush();
    }

    @Override
    public void update(T entity) {
        em.merge(entity);
    }
}
