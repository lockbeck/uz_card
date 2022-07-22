package com.company.entity;

import com.company.enums.CardStatus;
import com.company.enums.GeneralRole;
import com.company.enums.GeneralStatus;
import com.company.enums.ProfileRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
public class ProfileEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
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




}
