package com.company.dto.company;

import com.company.enums.CompanyRole;
import com.company.enums.GeneralRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CompanyCreateDTO {
    @NotBlank(message = "name required MAZGI")
    private String name;
    @NotBlank(message = "username required MAZGI")
    private String username;//unique
    @NotBlank(message = "password required MAZGI")
    private String password;
    @NotBlank(message = "address required MAZGI")
    private String address;
    @NotBlank(message = "contact required MAZGI")
    private String contact;
    @NotBlank(message = "contact required MAZGI")
    private Double servicePercentage;

    @NotBlank(message = "role required MAZGI")
    private GeneralRole role;
}
