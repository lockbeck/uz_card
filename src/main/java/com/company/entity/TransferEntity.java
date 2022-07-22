package com.company.entity;

import com.company.enums.ProfileRole;
import com.company.enums.TransferStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer")
@Getter
@Setter
@NoArgsConstructor
public class TransferEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column
    private Long totalAmount;
    @Column
    private Long amount;
    @Column
    private Long serviceAmount;
    @Column
    private Long servicePercentage;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @Column(name = "company_id")
    private String companyId;
    @JoinColumn(name = "company_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;

    @Column(name = "from_card_id")
    private String fromCardId;
    @JoinColumn(name = "from_card_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CardEntity fromCard;
    @Column(name = "to_card_id")
    private String toCardId;
    @JoinColumn(name = "to_card_id",insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CardEntity toCard;

}
