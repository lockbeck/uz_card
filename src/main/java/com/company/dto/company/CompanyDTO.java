package com.company.dto.company;

import com.company.dto.card.CardDTO;
import com.company.enums.CompanyRole;
import com.company.enums.GeneralRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CompanyDTO {

    private String id;
    private String name;
    private String username;//unique
    private String password;
    private String address;
    private String contact;
    private Double servicePercentage;
    private LocalDateTime createdDate;
    private Boolean visible;
    private GeneralRole role;
    private CardDTO card;


    public CompanyDTO(String id) {
        this.id = id;
    }
}
