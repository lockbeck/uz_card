package com.company.service;

import com.company.dto.company.CompanyDTO;
import com.company.entity.CompanyEntity;
import com.company.entity.TransactionEntity;
import com.company.enums.TransactionStatus;
import com.company.enums.TransactionType;
import com.company.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    @Lazy
    private TransferService transferService;

    @Value("${uzCardNumberId}")
    private String uzCardNumberId;

    public void creditFromCard(String cardId, Long amount, String transferId) {
        TransactionEntity entity= new TransactionEntity();
        entity.setCard(cardService.get(cardId));
        entity.setAmount(amount);
        entity.setTransfer(transferService.get(transferId));
        entity.setType(TransactionType.CREDIT);
        entity.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(entity);
    }
    public void debitToCard(String cardId, Long amount, String transferId) {
        TransactionEntity entity= new TransactionEntity();
        entity.setCard(cardService.get(cardId));
        entity.setAmount(amount);
        entity.setTransfer(transferService.get(transferId));
        entity.setType(TransactionType.DEBIT);
        entity.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(entity);
    }

    public void debitToUzCard( Long amount, String transferId) {
        TransactionEntity entity= new TransactionEntity();
        entity.setCard(cardService.get(uzCardNumberId));
        entity.setAmount( amount);
        entity.setTransfer(transferService.get(transferId));
        entity.setType(TransactionType.DEBIT);
        entity.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(entity);
    }

    public void debitToCompanyCard(CompanyEntity company, Long amount, String transferId) {
        TransactionEntity entity= new TransactionEntity();
        entity.setCard(cardService.get(company.getCard().getId()));
        entity.setAmount(amount);
        entity.setTransfer(transferService.get(transferId));
        entity.setType(TransactionType.DEBIT);
        entity.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(entity);
    }
}
