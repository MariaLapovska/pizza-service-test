package com.projects.pizzaservice.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mariia Lapovska
 */
@Getter
@Setter
@Component
public class DiscountManager {

    private List<Discount> discounts;

    @Autowired
    public DiscountManager(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public BigDecimal applyDiscounts(Order order) {
        for (Discount discount : discounts) {
            order.addToDiscountAmount(discount.calculateDiscount(order));
        }

        return order.getDiscountAmount();
    }
}
