package com.example.lab5.dao.impl.jpa;

import com.example.lab5.dao.OrderDao;
import com.example.lab5.entity.Order;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class JpaOrderDao extends JpaAbstractDao<Order> implements OrderDao {
    public JpaOrderDao() {
        super(Order.class);
    }

    @Override
    public List<Order> findFiltered(Boolean processed, int page, int size) {

        String jpql = "SELECT o FROM Order o" + (processed != null ? " WHERE o.processed = :processed" : "");
        TypedQuery<Order> query = em.createQuery(jpql, Order.class);

        if (processed != null) {
            query.setParameter("processed", processed);
        }

        return query.setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
