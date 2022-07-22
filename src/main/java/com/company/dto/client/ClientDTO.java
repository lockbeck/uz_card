package com.company.dto.client;

import com.company.enums.GeneralStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ClientDTO {
    private String id;
    private String name;
    private String surname;
    private String middleName;
    private String phone;
    private String passportSeria;
    private String passportNumber;
    private LocalDateTime createdDate;
    private Boolean visible;
    private GeneralStatus status;

    public ClientDTO(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public ClientDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
