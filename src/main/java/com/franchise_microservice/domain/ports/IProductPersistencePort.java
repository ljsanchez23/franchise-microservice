package com.franchise_microservice.domain.ports;

import com.franchise_microservice.domain.model.Product;

import java.util.Optional;

public interface IProductPersistencePort {
    void saveProduct(Product product, String branchName);
    Optional<Product> findByName(String name);
    void updateName(String currentName, String newName);
}
