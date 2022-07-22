package com.company.entity;

import com.company.enums.TransactionStatus;
import com.company.enums.TransactionType;
import com.company.enums.TransferStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column
    private Long amount;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    @JoinColumn(name = "card_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CardEntity card;

    @JoinColumn(name = "transfer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TransferEntity transfer;


}
