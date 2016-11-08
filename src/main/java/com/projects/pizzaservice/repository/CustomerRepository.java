package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Customer;

import java.util.List;

/**
 * @author Mariia Lapovska
 */
public interface CustomerRepository {
    Customer add(Customer customer);

    Customer find(Integer id);

    List<Customer> findAll();
}
