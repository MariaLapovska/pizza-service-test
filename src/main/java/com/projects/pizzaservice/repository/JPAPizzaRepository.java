package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Pizza;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * @author Mariia Lapovska
 */
@Repository
@Transactional
public class JPAPizzaRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pizza add(Pizza pizza) {
        if (pizza.getId() == null) {
            entityManager.persist(pizza);
        } else {
            pizza = entityManager.merge(pizza);
        }

        return pizza;
    }

    @Override
    @Transactional(readOnly = true)
    public Pizza find(Integer id) {
        return entityManager.find(Pizza.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pizza> findAll() {
        return entityManager.createNamedQuery("Pizza.findAll", Pizza.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pizza> findAllByType(Pizza.Type type) {
        return entityManager.createNamedQuery("Pizza.findAllByType", Pizza.class)
                .setParameter("type", type)
                .getResultList();
    }
}