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
@Table(name = "ADDRESS")
public class Address implements Serializable {

    @TableGenerator(
            name = "addressGen",
            table = "ADDRESS_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "ADDRESS_ID",
            allocationSize = 20
    )

    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "addressGen")
    private Integer id;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "APARTMENT", nullable = false)
    private Integer apartment;
}