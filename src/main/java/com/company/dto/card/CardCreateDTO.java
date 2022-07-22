package com.company.dto.card;

import com.company.dto.client.ClientDTO;
import com.company.dto.company.CompanyDTO;
import com.company.enums.CardStatus;
import com.company.enums.GeneralRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CardCreateDTO {
    private LocalDate expiredDate;
    private Long balance;
    private String phone;
    private String companyId;
    private String clientId;
    private CardStatus status;


}