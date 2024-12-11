package com.franchise_microservice.domain.service;

import com.franchise_microservice.domain.model.Franchise;
import com.franchise_microservice.domain.model.Product;

import java.util.List;

public interface IFranchiseServicePort {
    void saveFranchise(Franchise franchise);
    void updateName(String currentName, String newName);
    List<Product> listHighestStock(String franchiseName);

}
