package com.projects.pizzaservice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Mariia Lapovska
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "PIZZA")
@NamedQueries({
        @NamedQuery(name = "Pizza.findAll",
                query = "SELECT p FROM Pizza p"),
        @NamedQuery(name = "Pizza.findAllByType",
                query = "SELECT p FROM Pizza p WHERE p.type = :type")
})
public class Pizza implements Serializable {

    @TableGenerator(
            name = "pizzaGen",
            table = "PIZZA_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "PIZZA_ID",
            allocationSize = 20
    )

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pizzaGen")
    @Column(name = "PIZZA_ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private Type type;

    public enum Type {
        VEGETARIAN, SEA, MEAT
    }
}
