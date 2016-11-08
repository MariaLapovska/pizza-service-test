package com.projects.pizzaservice.domain;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mariia Lapovska
 */
@NoArgsConstructor
@Data
@Component
@Scope("prototype")
@Entity
@Table(name = "PIZZA_ORDER")
@NamedQueries({
        @NamedQuery(name = "Order.findAll",
                query = "SELECT o FROM Order o"),
        @NamedQuery(name = "Order.findAllByCustomer",
                query = "SELECT o FROM Order o WHERE o.customer = :customer")
})
public class Order implements Serializable {

    @TableGenerator(
            name = "orderGen",
            table = "ORDER_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "ORDER_ID",
            allocationSize = 20
    )

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "orderGen")
    private Integer id;

    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private Address address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PIZZAS_IN_ORDER")
    @MapKeyJoinColumn(name = "PIZZA_ID")
    @MapKeyColumn(name = "ORDER_ID")
    @Column(name = "AMOUNT")
    private Map<Pizza, Integer> pizzas = new HashMap<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status = Status.NEW;

    private transient BigDecimal discountAmount = BigDecimal.ZERO;

    public Order(Integer id, Customer customer, Address address) {
        this.id = id;
        this.customer = customer;
        this.address = address;
    }

    public void addToDiscountAmount(BigDecimal amountToAdd) {
        discountAmount = discountAmount.add(amountToAdd);
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void addPizza(Pizza pizza) {
        pizzas.merge(pizza, 1, Integer::sum);
    }

    public int getPizzasQuantity() {
        return pizzas.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas.stream().collect(
                Collectors.toMap(s -> s, s -> 1, Integer::sum)
        );
    }

    public boolean progress() {
        return setStatus(Status.IN_PROGRESS);
    }

    public boolean cancel() {
        return setStatus(Status.CANCELLED);
    }

    public boolean close() {
        if (setStatus(Status.DONE)) {
            putTotalOnMemberCard();

            return true;
        }

        return false;
    }

    public BigDecimal getTotal() {
        return getTotalWithoutDiscount().subtract(discountAmount);
    }

    public BigDecimal getTotalWithoutDiscount() {
        BigDecimal price = BigDecimal.ZERO;

        for (Map.Entry<Pizza, Integer> pizzaEntry : pizzas.entrySet()) {
            price = price.add(pizzaEntry.getKey().getPrice().multiply(new BigDecimal(pizzaEntry.getValue())));
        }

        return price;
    }

    private boolean setStatus(Status newStatus) {
        if (this.status.isTransmutableTo(newStatus)) {
            this.status = newStatus;

            return true;
        }

        return false;
    }

    private boolean putTotalOnMemberCard() {
        if (customer.getMemberCard() != null) {
            customer.getMemberCard().addToBalance(getTotal());

            return true;
        }

        return false;
    }

    public enum Status {
        NEW("IN_PROGRESS", "CANCELLED", "DONE"),
        IN_PROGRESS("DONE"),
        CANCELLED(),
        DONE();

        private final List<String> allowedTransitions;

        Status(String... statuses) {
            this.allowedTransitions = Arrays.asList(statuses);
        }

        boolean isTransmutableTo(Status status) {
            return allowedTransitions.contains(status.name());
        }
    }
}
