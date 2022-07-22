package com.company.repository;

import com.company.entity.ClientEntity;
import com.company.entity.CompanyEntity;
import com.company.entity.ProfileEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, String> {

    Optional<ClientEntity> findByPassportNumberAndPassportSeria(String passportSeria , String passportNumber);
}
