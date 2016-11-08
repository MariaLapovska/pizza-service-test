package com.projects.pizzaservice.domain;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Mariia Lapovska
 */
@Component
@org.springframework.core.annotation.Order(value = Ordered.LOWEST_PRECEDENCE)
public class MemberCardDiscount implements Discount {

    private static final BigDecimal MEMBER_CARD_DISCOUNT = new BigDecimal(0.1);
    private static final BigDecimal MAX_DISCOUNT = new BigDecimal(0.3);

    @Override
    public BigDecimal calculateDiscount(Order order) {
        MemberCard memberCard = order.getCustomer().getMemberCard();

        if (memberCard != null) {
            BigDecimal cardDiscount = memberCard.getBalance().multiply(MEMBER_CARD_DISCOUNT);
            BigDecimal pricePercent = order.getTotal().multiply(MAX_DISCOUNT);

            if (pricePercent.compareTo(cardDiscount) < 0) {
                return pricePercent;
            } else {
                return cardDiscount;
            }
        } else {
            return BigDecimal.ZERO;
        }
    }
}