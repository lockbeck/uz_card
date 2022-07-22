package com.company.service;

import com.company.dto.company.CompanyCreateDTO;
import com.company.dto.company.CompanyDTO;
import com.company.dto.profile.ProfileCreateDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.entity.CompanyEntity;
import com.company.entity.ProfileEntity;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.CompanyRepository;
import com.company.repository.ProfileRepository;
import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;



    public ProfileDTO create(ProfileCreateDTO dto) {
        Optional<ProfileEntity> byUsername = profileRepository.findByUsername(dto.getUsername());
        if (byUsername.isPresent()) {
            throw new BadRequestException("profile already exists");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setRole(dto.getRole());
        profileRepository.save(entity);
        return new ProfileDTO(entity.getId(), dto.getName() , dto.getUsername());

    }

    public String update(ProfileCreateDTO dto, String id) {
        ProfileEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setRole(dto.getRole());
        profileRepository.save(entity);
        return "Profile successfully updated";


    }


    public PageImpl paginationFilter(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProfileEntity> all = profileRepository.findAll(pageable);
        List<ProfileDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setId(entity.getSurname());
            dto.setUsername(entity.getUsername());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setRole(entity.getRole());
            dto.setVisible(entity.getVisible());

            dtoList.add(dto);
        });
        return new PageImpl(dtoList, pageable, all.getTotalElements());

    }


    public ProfileEntity get(String id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Company not found");
        });
    }

    public ProfileEntity getForJwt(String id) {
        return profileRepository.findById(id).get();
    }


    public boolean hasById(String id) {
        return profileRepository.existsById(id);
    }
}
