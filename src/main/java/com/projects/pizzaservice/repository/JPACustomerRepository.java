package com.projects.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.projects.pizzaservice.domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Mariia Lapovska
 */
@Repository
@Transactional
public class JPACustomerRepository implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer add(Customer customer) {
        if (customer.getId() == null) {
            entityManager.persist(customer);
        } else {
            customer = entityManager.merge(customer);
        }

        return customer;
    }

    @Override
    @Transactional(readOnly = true)
    public Customer find(Integer id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return entityManager.createNamedQuery("Customer.findAll", Customer.class)
                .getResultList();
    }
}