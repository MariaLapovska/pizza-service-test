package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Address;

/**
 * @author Mariia Lapovska
 */
public interface AddressRepository {
    Address add(Address address);

    Address find(Integer id);
}
