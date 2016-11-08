package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Order;
import com.projects.pizzaservice.domain.Customer;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * @author Mariia Lapovska
 */
@Repository
@Transactional
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order add(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
        } else {
            order = entityManager.merge(order);
        }

        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public Order find(Integer id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return entityManager.createNamedQuery("Order.findAll", Order.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAllByCustomer(Customer customer) {
        return entityManager.createNamedQuery("Order.findAllByCustomer", Order.class)
                .setParameter("customer", customer)
                .getResultList();
    }
}
