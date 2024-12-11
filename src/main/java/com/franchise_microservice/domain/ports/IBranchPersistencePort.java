package com.franchise_microservice.domain.ports;

import com.franchise_microservice.domain.model.Branch;

import java.util.Optional;

public interface IBranchPersistencePort {
    void saveBranch(Branch branch, String franchiseName);
    Optional<Branch> findByName(String name);
    boolean deleteProductFromBranch(String productName, String branchName);
    void updateName(String currentName, String newName);
}
