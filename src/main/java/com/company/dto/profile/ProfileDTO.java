package com.company.dto.profile;

import com.company.enums.GeneralRole;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private GeneralRole role;
    private ProfileStatus status;
    private LocalDateTime createdDate;
    private Boolean visible;
    private String jwt;

    public ProfileDTO(String id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }
}
