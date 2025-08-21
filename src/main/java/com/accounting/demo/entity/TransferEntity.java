package com.accounting.demo.entity;

import com.accounting.demo.enumerator.TransactionTypeEnum;
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
        name = "transfers",
        indexes = {
                @Index(name = "ix_tx_account", columnList = "account_id"),
                @Index(name = "ix_tx_beneficiary", columnList = "beneficiary_account_id")
        },
        schema = "public"
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class TransferEntity {

    @Id
    @SequenceGenerator(
            name = "transfer_seq",
            sequenceName = "transfer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transfer_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_tx_account"))
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_account_id",
            foreignKey = @ForeignKey(name = "fk_tx_beneficiary"))
    private AccountEntity beneficiaryAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 6) // CREDIT / DEBIT
    private TransactionTypeEnum type;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @CreatedDate
    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;
}