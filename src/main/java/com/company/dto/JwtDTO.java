package com.company.dto;

import com.company.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private String id;
    private ProfileRole role;

    public JwtDTO(String id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }
}
