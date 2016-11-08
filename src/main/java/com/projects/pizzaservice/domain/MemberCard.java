package com.projects.pizzaservice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mariia Lapovska
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "MEMBER_CARD")
public class MemberCard implements Serializable {

    @TableGenerator(
            name = "cardGen",
            table = "CARD_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "CARD_ID",
            allocationSize = 20
    )

    @Id
    @Column(name = "CARD_ID")
    @GeneratedValue(generator = "cardGen")
    private Integer id;

    @Column(name = "ISSUE_DATE", nullable = false)
    private LocalDate issueDate;

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    public void addToBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}