package com.company.controller;

import com.company.dto.card.CardCreateDTO;
import com.company.dto.card.CardDTO;
import com.company.dto.company.CompanyCreateDTO;
import com.company.enums.CardStatus;
import com.company.service.CardService;
import com.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {

    @Autowired
    @Lazy
    private CardService cardService;

    @PreAuthorize("hasRole('ROLE_BANK')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardCreateDTO dto) {
        String response = cardService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_BANK')")
    @PutMapping("/changeStatusBank/{id}")
    public ResponseEntity<?> changeStatusBank(@PathVariable("id") String id, @RequestBody CardCreateDTO dto) {
        String response = cardService.changeStatus(dto.getStatus(), id);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ROLE_PAYMENT')")
    @PutMapping("/changeStatusPayment/{id}")
    public ResponseEntity<?> changeStatusPayment(@PathVariable("id") String id) {
        String response = cardService.changeStatus(CardStatus.BLOCK, id);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ROLE_BANK')")
    @GetMapping("/assignPhone/{id}")
    public ResponseEntity<?> assignPhone(@PathVariable("id") String id, @RequestBody CardCreateDTO dto) {
        String response = cardService.assignPhone(dto.getPhone(), id);
        return ResponseEntity.ok().body(response);
    }
    @PreAuthorize("hasRole('ROLE_PAYMENT')")
    @PutMapping("/payment/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        CardDTO response = cardService.getByIdPayment(id);
        return ResponseEntity.ok().body(response);
    }


//    @GetMapping("/adm/pagination")
//    public ResponseEntity<?> pagination(//@RequestParam("page") Integer page , @RequestParam("size") Integer size
//                                        @RequestParam(value = "page", defaultValue = "1")int page,
//                                        @RequestParam(value = "size", defaultValue = "5") int size)
//    {
//        PageImpl pagination = companyService.pagination(page, size);
//        return ResponseEntity.ok(pagination);
//    }
//
//    @DeleteMapping("/adm/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id") String id) {
//
//        companyService.delete(id);
//        return ResponseEntity.ok().body("Company is deleted");
//    }
}
