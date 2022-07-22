package com.company.service;
//User :Lenovo
//Date :18.07.2022
//Time :22:18
//Project Name :app-uz-card

import com.company.config.CustomUserDetails;
import com.company.dto.AuthResponseDTO;
import com.company.dto.LoginDTO;
import com.company.repository.ProfileRepository;
import com.company.util.JwtUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Api(tags = "Auth Service")
public class AuthService {

    //Repository
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthResponseDTO login(LoginDTO auth) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        CustomUserDetails principal = (CustomUserDetails) authenticate.getPrincipal();

        AuthResponseDTO authResponse=new AuthResponseDTO();
        authResponse.setUsername(principal.getUsername());
        authResponse.setJwt(JwtUtil.encode(principal.getId()));
        return authResponse;

    }
}
