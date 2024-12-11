package com.franchise_microservice.adapters.driven.jpa.mysql.adapter;

import com.franchise_microservice.adapters.driven.jpa.mysql.entity.BranchEntity;
import com.franchise_microservice.adapters.driven.jpa.mysql.entity.ProductEntity;
import com.franchise_microservice.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.franchise_microservice.adapters.driven.jpa.mysql.repository.IBranchRepository;
import com.franchise_microservice.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.franchise_microservice.domain.exception.BranchNotFoundException;
import com.franchise_microservice.domain.model.Product;
import com.franchise_microservice.domain.ports.IProductPersistencePort;
import com.franchise_microservice.domain.util.Constants;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductAdapter implements IProductPersistencePort {
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;
    private final IBranchRepository branchRepository;

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name)
                .map(productEntityMapper::toModel);
    }

    @Override
    public void saveProduct(Product product, String branchName) {
        Optional<BranchEntity> existingBranch = branchRepository.findByName(branchName);

        BranchEntity branchEntity = existingBranch
                .orElseThrow(() -> new BranchNotFoundException(Constants.BRANCH_NOT_FOUND_ERROR_MESSAGE));

        Optional<ProductEntity> existingProduct = productRepository.findByName(product.getName());

        existingProduct.ifPresentOrElse(
                existingEntity -> {
                    existingEntity.setName(product.getName());
                    existingEntity.setStock(product.getStock());
                    existingEntity.setBranch(branchEntity);
                    productRepository.save(existingEntity);
                },
                () -> {
                    ProductEntity newProductEntity = productEntityMapper.toEntity(product);
                    newProductEntity.setBranch(branchEntity);
                    newProductEntity.setBranchName(branchName);
                    productRepository.save(newProductEntity);
                }
        );
    }

    @Override
    public void updateName(String currentName, String newName){
        productRepository.updateName(currentName, newName);
    }
}
