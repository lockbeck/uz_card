package com.company.dto.profile;

import com.company.enums.GeneralRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ProfileCreateDTO {

    private String name;
    private String surname;
    private String username;
    private String password;
    private GeneralRole role;
}
