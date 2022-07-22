package com.company.service;

import com.company.dto.card.CardDTO;
import com.company.dto.client.ClientDTO;
import com.company.dto.company.CompanyDTO;
import com.company.dto.transfer.TransferCreateDTO;
import com.company.dto.transfer.TransferDTO;
import com.company.dto.transfer.TransferResponseDTO;
import com.company.entity.CardEntity;
import com.company.entity.CompanyEntity;
import com.company.entity.TransferEntity;
import com.company.enums.CardStatus;
import com.company.enums.TransferStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TransactionService transactionService;

    @Value("${uzCardServicePercentage}")
    private Double uzCardServicePercentage;
//    final Double uzCardServicePercentage = 0.5;
    @Value("${uzCardNumberId}")
    private String uzCardNumberId;


    public TransferResponseDTO create(TransferCreateDTO transferDTO) {
        if (transferDTO.getAmount()<1000) {
            throw new BadRequestException("Minimal transfer amount 1000 sum");
        }
        System.out.println(transferDTO.getFromCard());
        System.out.println(transferDTO.getToCard());
        CardEntity fromCard = cardService.getByNumber(transferDTO.getFromCard());
        CardEntity toCard = cardService.getByNumber(transferDTO.getToCard());
        CompanyEntity company = companyService.get(transferDTO.getCompanyId());

        if (!fromCard.getStatus().equals(CardStatus.ACTIVE)) {
            return new TransferResponseDTO("failed", "Card not active");
        }

        if (!toCard.getStatus().equals(CardStatus.ACTIVE)) {
            return new TransferResponseDTO("failed", "Card not active");
        }

        // 10,000
        //  company.getServicePercentage()  0.3
        //  0.4 - uzcard
        double service_percentage = uzCardServicePercentage + company.getServicePercentage(); // 0.7
        double service_amount = (transferDTO.getAmount() * service_percentage)/100; // 70
        double total_amount = transferDTO.getAmount() + service_amount; // 10,070

        if (fromCard.getBalance() < total_amount) {
            return new TransferResponseDTO("failed", "Not enough money");
        }

        TransferEntity entity = new TransferEntity();

        entity.setFromCardId(fromCard.getId());
        entity.setToCardId(toCard.getId());
        entity.setCompanyId(transferDTO.getCompanyId());

        entity.setAmount(transferDTO.getAmount());
        entity.setServicePercentage((long) service_percentage);
        entity.setServiceAmount((long) service_amount);
        entity.setTotalAmount((long) total_amount);

        entity.setStatus(TransferStatus.IN_PROGRESS);
        // ..........
        transferRepository.save(entity);

        System.out.println("transfer save boldi");
        // id, card ,... total_amount
        TransferDTO dto = new TransferDTO();

        CardDTO toCardDTo = new CardDTO(toCard.getId(), toCard.getNumber(), new ClientDTO(toCard.getClient().getName(), toCard.getClient().getSurname()));
        dto.setToCard(toCardDTo);
        dto.setFromCard(new CardDTO(fromCard.getId(), fromCard.getNumber()));
        dto.setCompany(new CompanyDTO(company.getId()));

        dto.setAmount(transferDTO.getAmount());
        dto.setServicePercentage(service_percentage);
        dto.setServiceAmount((long) service_amount);
        dto.setTotalAmount((long) total_amount);

        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());


        return new TransferResponseDTO("success", "", dto);
    }

    public void finishTransfer(String transferId) {
        // 1. fromCard - CREDIT

        Optional<TransferEntity> byId = transferRepository.findById(transferId);
        if(byId.isEmpty()){
            throw new ItemNotFoundException("transfer not found");
        }
        TransferEntity entity = byId.get();
        if(entity.getStatus().equals(TransferStatus.SUCCESS)){
            throw new BadRequestException("transfer already finished");
        }
        transactionService.creditFromCard(entity.getFromCardId(),entity.getTotalAmount(),entity.getId());
        // 2. TOCARD - DEBIT
        transactionService.debitToCard(entity.getToCardId(),entity.getAmount(),entity.getId());
        // 3. card_id (PAYMENT,BANK) - DEBIT
        double debitToUzCard = uzCardServicePercentage* entity.getServiceAmount();
        transactionService.debitToUzCard((long) debitToUzCard,entity.getId());
        // 4. card_id  (UZ_CARD) - DEBIT
        CompanyEntity company = companyService.get(entity.getCompanyId());
        double debitToCompanyCard = company.getServicePercentage() * entity.getServiceAmount();
        transactionService.debitToCompanyCard(company, (long) debitToCompanyCard,entity.getId());


        // FROM_CARD_UPDATE
        cardService.minus(entity.getFromCardId(),entity.getTotalAmount());
        // TO_CARD_UPDATE
        cardService.plus(entity.getToCardId(), entity.getAmount());
        // Company_CARD_UPDATE
        cardService.plus(company.getCard().getId(), (long) debitToCompanyCard);
        // Uzcard_CARD_UPDATE
        cardService.plus(uzCardNumberId, (long) debitToUzCard);

        // Transfer status update
        transferRepository.changeStatus(entity.getId(),TransferStatus.SUCCESS);
    }

    public TransferEntity get(String id){
        return transferRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("transfer not found");
        });
    }

}
