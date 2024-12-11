package com.franchise_microservice.domain.service.impl;

import com.franchise_microservice.domain.exception.FranchiseAlreadyExistsException;
import com.franchise_microservice.domain.exception.FranchiseNotFoundException;
import com.franchise_microservice.domain.model.Branch;
import com.franchise_microservice.domain.model.Franchise;
import com.franchise_microservice.domain.model.Product;
import com.franchise_microservice.domain.ports.IFranchisePersistencePort;
import com.franchise_microservice.domain.service.IFranchiseServicePort;
import com.franchise_microservice.domain.util.Constants;

import java.util.List;
import java.util.Objects;

public class FranchiseUseCase implements IFranchiseServicePort {
    private final IFranchisePersistencePort franchisePersistencePort;

    public FranchiseUseCase(IFranchisePersistencePort franchisePersistencePort) {
        this.franchisePersistencePort = franchisePersistencePort;
    }

    @Override
    public void saveFranchise(Franchise franchise){
        franchisePersistencePort.findByName(franchise.getName())
                .ifPresent(existing -> {
                    throw new FranchiseAlreadyExistsException(Constants.FRANCHISE_ALREADY_EXISTS_ERROR_MESSAGE);
                });
        franchisePersistencePort.saveFranchise(franchise);
    }

    @Override
    public void updateName(String currentName, String newName) {
        franchisePersistencePort.findByName(currentName)
                .ifPresentOrElse(
                        franchise -> {
                            franchise.setName(newName);
                            franchisePersistencePort.updateName(currentName, newName);
                        },
                        () -> {
                            throw new FranchiseNotFoundException(Constants.FRANCHISE_NOT_FOUND_ERROR_MESSAGE);
                        }
                );
    }

    @Override
    public List<Product> listHighestStock(String franchiseName) {
        Franchise franchise = franchisePersistencePort.findByName(franchiseName)
                .orElseThrow(() -> new FranchiseNotFoundException(Constants.FRANCHISE_NOT_FOUND_ERROR_MESSAGE));

        List<Branch> branches = franchise.getBranches();

        return branches.stream()
                .map(branch -> branch.getProducts().stream()
                        .max((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()))
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }


}
