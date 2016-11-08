package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Pizza;

import java.util.List;

/**
 * @author Mariia Lapovska
 */
public interface PizzaRepository {
    Pizza add(Pizza pizza);

    Pizza find(Integer id);

    List<Pizza> findAll();

    List<Pizza> findAllByType(Pizza.Type type);
}