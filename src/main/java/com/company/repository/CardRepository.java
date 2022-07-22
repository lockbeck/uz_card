package com.company.repository;

import com.company.entity.CardEntity;
import com.company.entity.CompanyEntity;
import com.company.entity.TransactionEntity;
import com.company.mapper.CardInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CardRepository extends PagingAndSortingRepository<CardEntity, String> {
    @Query("from CardEntity where number = :number")
    Optional<CardEntity> findByNumber(@Param("number") String number);

    @Query(value = " select cr.id as id, cr.number as number, cr.expired_date as expiredDate" +
            " cr.phone as phone, cr.status as status, cr.created_date as createdDate ," +
            " cr.balance as balance , cl.id as clientId, cl.name as clientaName, cl.surname as clientSurname  " +
            " from card as cr inner join client as cl on cr.client_id = cl.id " +
            " where visible = true order  by created_date",nativeQuery = true)
    CardInfo getById(String id);

    @Modifying
    @Transactional
    @Query("update CardEntity set balance = balance - ?2 where id = ?1")
    void minus(String cardId, Long amount);
    @Modifying
    @Transactional
    @Query("update CardEntity set balance = balance + ?2 where id = ?1")
    void plus(String cardId, Long amount);
}
