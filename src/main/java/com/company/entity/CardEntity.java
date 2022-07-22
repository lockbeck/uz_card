package com.company.entity;

import com.company.enums.CardStatus;
import com.company.enums.ProfileRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(unique = true)
    private String number;
    @Column
    private LocalDate expiredDate;
    @Column
    private Long balance;
    @Column
    private String phone;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column
    private Boolean visible = Boolean.TRUE;
    @Column
    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @Column(name = "company_id")
    private String companyId;
    @JoinColumn(name = "company_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;

    @Column(name = "client_id")
    private String clientId;
    @JoinColumn(name = "client_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientEntity client;


}
