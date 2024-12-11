package com.franchise_microservice.adapters.driven.jpa.mysql.adapter;

import com.franchise_microservice.adapters.driven.jpa.mysql.entity.BranchEntity;
import com.franchise_microservice.adapters.driven.jpa.mysql.entity.FranchiseEntity;
import com.franchise_microservice.adapters.driven.jpa.mysql.mapper.IBranchEntityMapper;
import com.franchise_microservice.adapters.driven.jpa.mysql.repository.IBranchRepository;
import com.franchise_microservice.adapters.driven.jpa.mysql.repository.IFranchiseRepository;
import com.franchise_microservice.domain.exception.BranchNotFoundException;
import com.franchise_microservice.domain.exception.FranchiseNotFoundException;
import com.franchise_microservice.domain.model.Branch;
import com.franchise_microservice.domain.ports.IBranchPersistencePort;
import com.franchise_microservice.domain.util.Constants;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class BranchAdapter implements IBranchPersistencePort {
    private final IBranchRepository branchRepository;
    private final IBranchEntityMapper branchEntityMapper;
    private final IFranchiseRepository franchiseRepository;


    @Override
    public Optional<Branch> findByName(String name) {
        return branchRepository.findByName(name)
                .map(branchEntityMapper::toModel);
    }

    @Override
    public boolean deleteProductFromBranch(String productName, String branchName) {
        BranchEntity branchEntity = branchRepository.findByName(branchName)
                .orElseThrow(() -> new BranchNotFoundException(Constants.BRANCH_NOT_FOUND_ERROR_MESSAGE));

        boolean removed = branchEntity.getProducts().removeIf(product -> product.getName().equalsIgnoreCase(productName));
        if (removed) {
            branchRepository.save(branchEntity);
        }
        return removed;
    }

    @Override
    public void saveBranch(Branch branch, String franchiseName) {
        Optional<FranchiseEntity> existingFranchise = franchiseRepository.findByName(franchiseName);

        FranchiseEntity franchiseEntity = existingFranchise
                .orElseThrow(() -> new FranchiseNotFoundException(Constants.FRANCHISE_NOT_FOUND_ERROR_MESSAGE));
        Optional<BranchEntity> existingBranch = branchRepository.findByName(branch.getName());

        existingBranch.ifPresentOrElse(
                existingEntity -> {
                    existingEntity.setFranchise(franchiseEntity);
                    existingEntity.setName(branch.getName());
                    branchRepository.save(existingEntity);
                },
                () -> {
                    BranchEntity newBranchEntity = branchEntityMapper.toEntity(branch);
                    newBranchEntity.setFranchise(franchiseEntity);
                    branchRepository.save(newBranchEntity);
                }
        );
    }

    @Override
    public void updateName(String currentName, String newName){
        branchRepository.updateName(currentName, newName);
    }

}
