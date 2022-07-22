package com.company.service;

import com.company.dto.company.CompanyCreateDTO;
import com.company.dto.company.CompanyDTO;
import com.company.entity.CompanyEntity;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.CompanyRepository;
import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CardService cardService;



    public String create(CompanyCreateDTO dto) {
        Optional<CompanyEntity> byUsername = companyRepository.findByUsername(dto.getUsername());
        if (byUsername.isPresent()) {
            throw new BadRequestException("company already exists");
        }
        CompanyEntity entity = new CompanyEntity();
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setAddress(dto.getAddress());
        entity.setContact(dto.getContact());
        entity.setServicePercentage(dto.getServicePercentage());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setRole(dto.getRole());
//        entity.setCardId(cardService.create());

        companyRepository.save(entity);
        return "Company successfully created";

    }

    public String update(CompanyCreateDTO dto, String id) {
        CompanyEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setAddress(dto.getAddress());
        entity.setContact(dto.getContact());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setRole(dto.getRole());
        companyRepository.save(entity);
        return "Company successfully updated";


    }
    public String delete( String id) {
        CompanyEntity entity = get(id);
        entity.setVisible(Boolean.FALSE);
        companyRepository.save(entity);
        return "Company successfully deleted";

    }

    public PageImpl pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CompanyEntity> all = companyRepository.findAll(pageable);
        List<CompanyDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> {
            CompanyDTO dto = new CompanyDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setUsername(entity.getUsername());
            dto.setAddress(entity.getAddress());
            dto.setContact(entity.getContact());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setRole(entity.getRole());
            dto.setVisible(entity.getVisible());

            dtoList.add(dto);
        });
        return new PageImpl(dtoList, pageable, all.getTotalElements());

    }


    public CompanyEntity get(String id) {
        return companyRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Company not found");
        });
    }

    public CompanyEntity getForJwt(String id) {
        return companyRepository.findById(id).get();
    }


    public boolean hasById(String id) {
      return companyRepository.existsById(id);
    }
}
