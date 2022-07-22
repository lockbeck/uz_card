package com.company.mapper;

import com.company.enums.CardStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CardInfo {
    String getId();
    String getNumber();
    LocalDate getExpiredDate();
    String getPhone();
    CardStatus getStatus();
    LocalDateTime getCreatedDate();
    Long getBalance();
    String getClientId();
    String getClientName();
    String getClientSurname();
}
