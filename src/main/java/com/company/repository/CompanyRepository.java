package com.company.repository;

import com.company.entity.CompanyEntity;
import com.company.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CompanyRepository extends PagingAndSortingRepository<CompanyEntity, String> {
    Optional<CompanyEntity> findByUsername(String username);
}
