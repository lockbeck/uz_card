package com.company.entity;

import com.company.enums.CompanyRole;
import com.company.enums.GeneralRole;
import com.company.enums.GeneralStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column
    private String name;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String address;
    @Column
    private String contact;
    @Column
    private Double servicePercentage;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column
    private Boolean visible = Boolean.TRUE;
    @Column
    @Enumerated(EnumType.STRING)
    private GeneralRole role;
    @Column
    @Enumerated(EnumType.STRING)
    private GeneralStatus status = GeneralStatus.ACTIVE;

    @Column(name = "card_id")
    private String cardId;
    @JoinColumn(name = "card_id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private CardEntity card;

}
