package com.company.dto;
//User :Lenovo
//Date :19.07.2022
//Time :20:54
//Project Name :app-uz-card

import com.company.enums.GeneralRole;
import lombok.Data;

import javax.persistence.*;

@Data
public class AuthResponseDTO {

    private String username;
    private String jwt;


}
