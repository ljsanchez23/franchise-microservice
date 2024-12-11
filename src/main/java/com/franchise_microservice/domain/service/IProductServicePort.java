package com.franchise_microservice.domain.service;

import com.franchise_microservice.domain.model.Product;

public interface IProductServicePort {
    void saveProduct(Product product, String branchName);
    void updateStock(String productName, Integer stock, String branchName);
    void updateName(String productName, String newName);
}
