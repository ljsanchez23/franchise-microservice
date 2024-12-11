package com.franchise_microservice.domain.ports;

import com.franchise_microservice.domain.model.Franchise;

import java.util.Optional;

public interface IFranchisePersistencePort {
    void saveFranchise(Franchise franchise);
    Optional<Franchise> findByName(String name);
    void updateName(String currentName, String newName);
}
