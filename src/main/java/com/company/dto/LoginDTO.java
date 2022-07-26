package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDTO {

    @NotBlank(message = "username required MAZGI")
    @Size(min = 1, max = 255,message = "title must be between 1 and 255 characters")
    private String username;
    @NotBlank(message = "password required MAZGI")
    private String password;
}
