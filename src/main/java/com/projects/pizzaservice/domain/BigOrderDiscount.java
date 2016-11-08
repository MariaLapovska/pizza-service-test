package com.projects.pizzaservice.domain;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Mariia Lapovska
 */
@Component
@org.springframework.core.annotation.Order(value = Ordered.HIGHEST_PRECEDENCE)
public class BigOrderDiscount implements Discount {

    private static final int PIZZAS_AMOUNT = 4;
    private static final BigDecimal DISCOUNT = new BigDecimal(0.3);

    @Override
    public BigDecimal calculateDiscount(Order order) {
        if (order.getPizzasQuantity() > PIZZAS_AMOUNT) {
            return getMostExpensivePizza(order).getPrice().multiply(DISCOUNT);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private Pizza getMostExpensivePizza(Order order) {
        SortedSet<Pizza> sortedPizzas = new TreeSet<>(
                (Pizza p1, Pizza p2) -> p1.getPrice().compareTo(p2.getPrice())
        );
        sortedPizzas.addAll(order.getPizzas().keySet());

        return sortedPizzas.last();
    }
}