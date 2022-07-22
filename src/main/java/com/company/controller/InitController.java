package com.company.controller;

import com.company.dto.company.CompanyCreateDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.GeneralRole;
import com.company.repository.ProfileRepository;
import com.company.service.CompanyService;
import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private ProfileRepository profileRepository;


    @GetMapping("/initAdmin")
    public ResponseEntity<?> init() {
        ProfileEntity entity  =new ProfileEntity();
        entity.setUsername("uz_card_Adminjon");
        entity.setSurname("Adminaliyev");
        entity.setName("Adminjon");
        entity.setPassword(MD5Util.getMd5("123"));
        entity.setRole(GeneralRole.ROLE_ADMIN);
        profileRepository.save(entity);
        return ResponseEntity.ok("Admin created");
    }
}
