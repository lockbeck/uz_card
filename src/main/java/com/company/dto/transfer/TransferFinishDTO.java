package com.company.dto.transfer;

import com.company.dto.card.CardDTO;
import com.company.dto.company.CompanyDTO;
import com.company.enums.TransferStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class TransferFinishDTO {
    // id (uuid), from_card_id, to_card_id, total_amount(5600),amount(5500),service_amount(100),
    //     service_percentage(1%),created_date, status(SUCCESS,FAILED,CANCELED), company_id

    private String id;
    private Long totalAmount;
    private Long amount;
    private Long serviceAmount;
    private double servicePercentage;
    private String companyId;
    private String fromCardId;
    private String toCardId;
}
