package com.company.service;

import com.company.dto.card.CardCreateDTO;
import com.company.dto.card.CardDTO;
import com.company.dto.client.ClientDTO;
import com.company.entity.CardEntity;
import com.company.enums.CardStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.mapper.CardInfo;
import com.company.repository.CardRepository;
import com.company.util.CardNumGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardNumGenerator cardNumGenerator;




    public String create(CardCreateDTO dto) {

        CardEntity entity = new CardEntity();
        entity.setNumber(cardNumGenerator.generate("9860",16));
        entity.setBalance(dto.getBalance());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
//        entity.setExpiredDate(LocalDate.parse(dto.getExpiredDate().toString(),formatter));
        if(dto.getPhone()!=null){
            entity.setPhone(dto.getPhone());
        }
        entity.setStatus(dto.getStatus());
        entity.setCompanyId(dto.getCompanyId());
        entity.setClientId(dto.getClientId());

        cardRepository.save(entity);
        return "Card successfully created";

    }

    public String changeStatus(CardStatus status , String id) {
        CardEntity entity = get(id);
        entity.setStatus(status);
        cardRepository.save(entity);
        return "Company successfully updated";
    }
    public CardDTO getByIdPayment(String id) {
        CardInfo byId = cardRepository.getById(id);
        CardDTO dto = new CardDTO();
        dto.setId(byId.getId());
        dto.setNumber(byId.getNumber());
        dto.setBalance(byId.getBalance());
        dto.setExpiredDate(byId.getExpiredDate());
        dto.setCreatedDate(byId.getCreatedDate());
        dto.setStatus(byId.getStatus());
        dto.setPhone(byId.getPhone());

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(byId.getClientId());
        clientDTO.setName(byId.getClientName());
        clientDTO.setSurname(byId.getClientSurname());
        dto.setClient(clientDTO);
        return dto;

    }

//    public PageImpl pagination(int page, int size) {
//        Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<CompanyEntity> all = companyRepository.findAll(pageable);
//        List<CompanyDTO> dtoList = new LinkedList<>();
//        all.forEach(entity -> {
//            CompanyDTO dto = new CompanyDTO();
//            dto.setId(entity.getId());
//            dto.setName(entity.getName());
//            dto.setUsername(entity.getUsername());
//            dto.setAddress(entity.getAddress());
//            dto.setContact(entity.getContact());
//            dto.setCreatedDate(entity.getCreatedDate());
//            dto.setRole(entity.getRole());
//            dto.setVisible(entity.getVisible());
//
//            dtoList.add(dto);
//        });
//        return new PageImpl(dtoList, pageable, all.getTotalElements());
//
//    }


    public CardEntity get(String id) {
        return cardRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Card not found");
        });
    }
    public CardEntity getByNumber(String number) {
        return cardRepository.findByNumber(number).orElseThrow(() -> {
            throw new ItemNotFoundException("Card not found");
        });
    }




    public String assignPhone(String phone, String id) {
        CardEntity entity = get(id);
        entity.setPhone(phone);
        cardRepository.save(entity);
        return "Phone is assigned to card";
    }

    public void minus(String cardId, Long amount) {
        cardRepository.minus(cardId,amount);
    }
    public void plus(String cardId, Long amount) {
        cardRepository.plus(cardId,amount);
    }
}
