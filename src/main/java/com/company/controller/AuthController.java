package com.company.controller;

import com.company.dto.AuthResponseDTO;
import com.company.dto.LoginDTO;
import com.company.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Authorization and Registration")
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Login", notes = "Method to login ")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
        log.info("Request for login {}", dto);
        AuthResponseDTO login = authService.login(dto);
        return ResponseEntity.ok(login);
    }
}
