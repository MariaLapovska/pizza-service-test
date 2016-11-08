package com.projects.pizzaservice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mariia Lapovska
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "CUSTOMER")
@NamedQueries({
        @NamedQuery(name = "Customer.findAll",
                query = "SELECT c FROM Customer c")
})
public class Customer implements Serializable {

    @TableGenerator(
            name = "customerGen",
            table = "CUSTOMER_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "CUSTOMER_ID",
            allocationSize = 20
    )

    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customerGen")
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CARD_ID", unique = true)
    private MemberCard memberCard;
}