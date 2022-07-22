package com.company.repository;

import com.company.entity.TransactionEntity;
import com.company.entity.TransferEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, String> {
}
