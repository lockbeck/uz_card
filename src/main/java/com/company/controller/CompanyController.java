package com.company.controller;

import com.company.dto.company.CompanyCreateDTO;
import com.company.dto.company.CompanyDTO;
import com.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    @Autowired
    @Lazy
    private CompanyService companyService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CompanyCreateDTO dto) {

        String response = companyService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody CompanyCreateDTO dto) {
        String response = companyService.update(dto, id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/adm/pagination")
    public ResponseEntity<?> pagination(//@RequestParam("page") Integer page , @RequestParam("size") Integer size
                                        @RequestParam(value = "page", defaultValue = "1")int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size)
    {
        PageImpl pagination = companyService.pagination(page, size);
        return ResponseEntity.ok(pagination);
    }

    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {

        companyService.delete(id);
        return ResponseEntity.ok().body("Company is deleted");
    }
}
