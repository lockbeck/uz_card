package com.company.repository;

import com.company.entity.TransferEntity;
import com.company.enums.TransferStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface TransferRepository extends CrudRepository<TransferEntity , String> {
    @Modifying
    @Transactional
    @Query("update TransferEntity set status = ?2 where id = ?1")
    void changeStatus(String id, TransferStatus status);
}
