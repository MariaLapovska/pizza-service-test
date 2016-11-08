package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.MemberCard;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Mariia Lapovska
 */
@Repository
@Transactional
public class JPAMemberCardRepository implements MemberCardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MemberCard add(MemberCard memberCard) {
        if (memberCard.getId() == null) {
            entityManager.persist(memberCard);
        } else {
            memberCard = entityManager.merge(memberCard);
        }

        return memberCard;
    }

    @Override
    @Transactional(readOnly = true)
    public MemberCard find(Integer id) {
        return entityManager.find(MemberCard.class, id);
    }
}
