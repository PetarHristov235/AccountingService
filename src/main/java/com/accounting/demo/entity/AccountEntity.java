package com.accounting.demo.entity;

import com.accounting.demo.enumerator.AccountStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_accounts_iban_name",
                        columnNames = {"iban", "name"}
                )
        },
        schema = "public"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AccountEntity {

    @Id
    @SequenceGenerator(
            name = "account_seq",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_seq"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 34)
    private String iban;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatusEnum status;

    @Column(nullable = false)
    private BigDecimal availableAmount;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @LastModifiedDate
    private LocalDateTime modifiedOn;

    @Version
    @Column(nullable = false)
    private Long version;
}
