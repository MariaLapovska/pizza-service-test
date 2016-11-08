package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.MemberCard;

/**
 * @author Mariia Lapovska
 */
public interface MemberCardRepository {
    MemberCard add(MemberCard memberCard);

    MemberCard find(Integer id);
}
