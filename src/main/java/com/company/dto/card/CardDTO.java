package com.company.dto.card;

import com.company.dto.client.ClientDTO;
import com.company.dto.company.CompanyDTO;
import com.company.entity.ClientEntity;
import com.company.entity.CompanyEntity;
import com.company.enums.CardStatus;
import com.company.enums.GeneralRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CardDTO {

    private String id;
    private String number;
    private LocalDate expiredDate;
    private Long balance;
    private String phone;
    private CompanyDTO company;
    private ClientDTO client;
    private CardStatus status;

    private Boolean visible;
    private LocalDateTime createdDate;


    public CardDTO(String id) {
        this.id = id;
    }

    public CardDTO(String id, String number) {
        this.id = id;
        this.number = number;
    }

    public CardDTO(String id, String number, ClientDTO client) {
        this.id = id;
        this.number = number;
        this.client = client;
    }
}
