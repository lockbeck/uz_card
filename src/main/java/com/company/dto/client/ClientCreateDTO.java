package com.company.dto.client;

import com.company.enums.GeneralRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ClientCreateDTO {

    private String name;
    private String surname;
    private String middleName;
    private String phone;
    private String passportSeria;
    private String passportNumber;
}
