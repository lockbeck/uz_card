package com.company.controller;

import com.company.dto.client.ClientCreateDTO;
import com.company.dto.client.ClientDTO;
import com.company.dto.profile.ProfileCreateDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.service.ClientService;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @PreAuthorize("hasAnyRole('ROLE_BANK', 'ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ClientCreateDTO dto) {

        ClientDTO response = clientService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody ClientCreateDTO dto) {
        String response = clientService.update(dto, id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/adm/pagination")
    public ResponseEntity<?> pagination(//@RequestParam("page") Integer page , @RequestParam("size") Integer size
                                        @RequestParam(value = "page", defaultValue = "1")int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size)
    {
        PageImpl pagination = clientService.paginationFilter(page, size);
        return ResponseEntity.ok(pagination);
    }

    @GetMapping("/adm/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String clientId)
    {
        ClientDTO dto = clientService.getById(clientId);
        return ResponseEntity.ok(dto);
    }


}
