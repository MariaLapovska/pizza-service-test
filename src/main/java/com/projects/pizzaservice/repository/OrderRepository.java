package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Customer;
import com.projects.pizzaservice.domain.Order;

import java.util.List;

/**
 * @author Mariia Lapovska
 */
public interface OrderRepository {
    Order add(Order order);

    Order find(Integer id);

    List<Order> findAll();

    List<Order> findAllByCustomer(Customer customer);
}
