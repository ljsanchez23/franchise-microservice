package com.franchise_microservice.domain.service.impl;

import com.franchise_microservice.domain.exception.BranchNotFoundException;
import com.franchise_microservice.domain.exception.ProductAlreadyExistsException;
import com.franchise_microservice.domain.exception.ProductNotFoundException;
import com.franchise_microservice.domain.model.Product;
import com.franchise_microservice.domain.ports.IBranchPersistencePort;
import com.franchise_microservice.domain.ports.IProductPersistencePort;
import com.franchise_microservice.domain.service.IProductServicePort;
import com.franchise_microservice.domain.util.Constants;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;
    private final IBranchPersistencePort branchPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort, IBranchPersistencePort branchPersistencePort) {
        this.productPersistencePort = productPersistencePort;
        this.branchPersistencePort = branchPersistencePort;
    }

    @Override
    public void saveProduct(Product product, String branchName) {
        branchPersistencePort.findByName(branchName)
                .ifPresentOrElse(
                        branch -> branch.addProduct(product),
                        () -> {
                            throw new BranchNotFoundException(Constants.BRANCH_NOT_FOUND_ERROR_MESSAGE);
                        }
                );

        productPersistencePort.findByName(product.getName())
                .ifPresent(existingProduct -> {
                    throw new ProductAlreadyExistsException(Constants.PRODUCT_ALREADY_EXISTS_ERROR_MESSAGE);
                });

        productPersistencePort.saveProduct(product, branchName);
    }

    @Override
    public void updateStock(String productName, Integer stock, String branchName) {
        productPersistencePort.findByName(productName)
                .ifPresentOrElse(
                        product -> {
                            product.setStock(stock);
                            productPersistencePort.saveProduct(product, branchName);
                        },
                        () -> {
                            throw new ProductNotFoundException(Constants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
                        }
                );
    }

    @Override
    public void updateName(String productName, String newName) {
        productPersistencePort.findByName(newName)
                .ifPresent(existingProduct -> {
                    throw new ProductAlreadyExistsException(Constants.PRODUCT_ALREADY_EXISTS_ERROR_MESSAGE);
                });

        productPersistencePort.findByName(productName)
                .ifPresentOrElse(
                        product -> {
                            product.setName(newName);
                            productPersistencePort.updateName(productName, newName);
                        },
                        () -> {
                            throw new ProductNotFoundException(Constants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
                        }
                );
    }
}
