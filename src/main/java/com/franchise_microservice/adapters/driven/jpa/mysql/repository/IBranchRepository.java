package com.franchise_microservice.adapters.driven.jpa.mysql.repository;

import com.franchise_microservice.adapters.driven.jpa.mysql.entity.BranchEntity;
import com.franchise_microservice.adapters.util.AdaptersConstants;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IBranchRepository extends JpaRepository<BranchEntity, Long> {
    Optional<BranchEntity> findByName(String name);
    @Modifying
    @Transactional
    @Query(AdaptersConstants.UPDATE_BRANCH_NAME_QUERY)
    void updateName(@Param(AdaptersConstants.UPDATE_BRANCH_NAME_QUERY_FIRST_PARAM) String currentName,
                    @Param(AdaptersConstants.UPDATE_BRANCH_NAME_QUERY_SECOND_PARAM) String newName);
}
