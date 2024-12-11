package com.franchise_microservice.domain.service.impl;

import com.franchise_microservice.domain.exception.BranchAlreadyExistsException;
import com.franchise_microservice.domain.exception.BranchNotFoundException;
import com.franchise_microservice.domain.exception.FranchiseNotFoundException;
import com.franchise_microservice.domain.exception.ProductNotFoundException;
import com.franchise_microservice.domain.model.Branch;
import com.franchise_microservice.domain.ports.IBranchPersistencePort;
import com.franchise_microservice.domain.ports.IFranchisePersistencePort;
import com.franchise_microservice.domain.service.IBranchServicePort;
import com.franchise_microservice.domain.util.Constants;

public class BranchUseCase implements IBranchServicePort {
    private final IBranchPersistencePort branchPersistencePort;
    private final IFranchisePersistencePort franchisePersistencePort;

    public BranchUseCase(IBranchPersistencePort branchPersistencePort, IFranchisePersistencePort franchisePersistencePort) {
        this.branchPersistencePort = branchPersistencePort;
        this.franchisePersistencePort = franchisePersistencePort;
    }

    @Override
    public void saveBranch(Branch branch, String franchiseName) {
        franchisePersistencePort.findByName(franchiseName)
                .ifPresentOrElse(
                        franchise -> franchise.addBranch(branch),
                        () -> {
                            throw new FranchiseNotFoundException(Constants.FRANCHISE_NOT_FOUND_ERROR_MESSAGE);
                        }
                );

        branchPersistencePort.findByName(branch.getName())
                .ifPresent(existingBranch -> {
                    throw new BranchAlreadyExistsException(Constants.BRANCH_ALREADY_EXISTS_ERROR_MESSAGE);
                });

        branchPersistencePort.saveBranch(branch, franchiseName);
    }

    @Override
    public void updateName(String currentName, String newName) {
        branchPersistencePort.findByName(newName)
                .ifPresent(existingBranch -> {
                    throw new BranchAlreadyExistsException(Constants.BRANCH_ALREADY_EXISTS_ERROR_MESSAGE);
                });

        branchPersistencePort.findByName(currentName)
                .ifPresentOrElse(
                        branch -> {
                            branch.setName(newName);
                            branchPersistencePort.updateName(currentName, newName);
                        },
                        () -> {
                            throw new BranchNotFoundException(Constants.BRANCH_NOT_FOUND_ERROR_MESSAGE);
                        }
                );
    }

    @Override
    public void deleteProduct(String branchName, String productName) {
        boolean removed = branchPersistencePort.deleteProductFromBranch(productName, branchName);

        if (!removed) {
            throw new ProductNotFoundException(Constants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
        }
    }

}
