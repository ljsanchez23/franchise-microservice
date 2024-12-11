package com.franchise_microservice.domain.service;

import com.franchise_microservice.domain.model.Branch;

public interface IBranchServicePort {
    void saveBranch(Branch branch, String franchiseName);
    void updateName(String currentName, String newName);
    void deleteProduct(String branchName, String productName);
}
