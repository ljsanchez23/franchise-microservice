package com.franchise_microservice.adapters.driven.jpa.mysql.repository;

import com.franchise_microservice.adapters.driven.jpa.mysql.entity.FranchiseEntity;
import com.franchise_microservice.adapters.util.AdaptersConstants;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IFranchiseRepository extends JpaRepository<FranchiseEntity, Long> {
    Optional<FranchiseEntity> findByName(String franchiseName);
    @Modifying
    @Transactional
    @Query(AdaptersConstants.UPDATE_FRANCHISE_NAME_QUERY)
    void updateName(@Param(AdaptersConstants.UPDATE_FRANCHISE_NAME_QUERY_FIRST_PARAM) String currentName,
                    @Param(AdaptersConstants.UPDATE_FRANCHISE_NAME_QUERY_SECOND_PARAM) String newName);
}
