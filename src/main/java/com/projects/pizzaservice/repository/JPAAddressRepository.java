package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Address;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * @author Mariia Lapovska
 */
@Repository
@Transactional
public class JPAAddressRepository implements AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address add(Address address) {
        if (address.getId() == null) {
            entityManager.persist(address);
        } else {
            address = entityManager.merge(address);
        }

        return address;
    }

    @Override
    @Transactional(readOnly = true)
    public Address find(Integer id) {
        return entityManager.find(Address.class, id);
    }
}
