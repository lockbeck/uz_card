package com.company.service;

import com.company.dto.client.ClientCreateDTO;
import com.company.dto.client.ClientDTO;
import com.company.dto.profile.ProfileCreateDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.entity.ClientEntity;
import com.company.entity.CompanyEntity;
import com.company.entity.ProfileEntity;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ClientRepository;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;



    public ClientDTO create(ClientCreateDTO dto) {
        Optional<ClientEntity> byUsername = clientRepository.findByPassportNumberAndPassportSeria(dto.getPassportSeria(),dto.getPassportNumber());

        if (byUsername.isPresent()) {
            throw new BadRequestException("client already exists");
        }
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setPassportSeria(dto.getPassportSeria());
        entity.setPassportNumber(dto.getPassportNumber());
        clientRepository.save(entity);
        return new ClientDTO(entity.getId(), dto.getName() , dto.getPhone());

    }

    public String update(ClientCreateDTO dto, String id) {
        ClientEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setPassportSeria(dto.getPassportSeria());
        entity.setPassportNumber(dto.getPassportNumber());
        clientRepository.save(entity);
        return "Client successfully updated";


    }


    public PageImpl paginationFilter(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ClientEntity> all = clientRepository.findAll(pageable);
        List<ClientDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> {
            ClientDTO dto = new ClientDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setPassportSeria(entity.getPassportSeria());
            dto.setPassportNumber(entity.getPassportNumber());
            dto.setVisible(entity.getVisible());

            dtoList.add(dto);
        });
        return new PageImpl(dtoList, pageable, all.getTotalElements());

    }
    public ClientDTO getById(String id) {
        ClientEntity entity = get(id);
            ClientDTO dto = new ClientDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setPassportSeria(entity.getPassportSeria());
            dto.setPassportNumber(entity.getPassportNumber());
            dto.setVisible(entity.getVisible());
            dto.setStatus(entity.getStatus());
            dto.setMiddleName(entity.getMiddleName());

          return dto;

    }



    public ClientEntity get(String id) {
        return clientRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Client not found");
        });
    }




}
