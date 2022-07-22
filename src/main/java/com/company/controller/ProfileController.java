package com.company.controller;

import com.company.dto.company.CompanyCreateDTO;
import com.company.dto.profile.ProfileCreateDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.service.CompanyService;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody ProfileCreateDTO dto) {

        ProfileDTO response = profileService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody ProfileCreateDTO dto) {
        String response = profileService.update(dto, id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/adm/pagination")
    public ResponseEntity<?> pagination(//@RequestParam("page") Integer page , @RequestParam("size") Integer size
                                        @RequestParam(value = "page", defaultValue = "1")int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size)
    {
        PageImpl pagination = profileService.paginationFilter(page, size);
        return ResponseEntity.ok(pagination);
    }


}
