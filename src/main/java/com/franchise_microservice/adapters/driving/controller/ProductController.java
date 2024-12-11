package com.franchise_microservice.adapters.driving.controller;

import com.franchise_microservice.adapters.driving.dto.request.ProductRequest;
import com.franchise_microservice.adapters.driving.mapper.request.IProductRequestMapper;
import com.franchise_microservice.adapters.util.AdaptersConstants;
import com.franchise_microservice.domain.service.IProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdaptersConstants.PRODUCT_CONTROLLER_URL)
@RequiredArgsConstructor
public class ProductController {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;

    @PostMapping(AdaptersConstants.CREATE_ENDPOINT)
    public ResponseEntity<Void> saveProduct(@RequestBody ProductRequest productRequest){
        productServicePort.saveProduct(productRequestMapper.toModel(productRequest), productRequest.getBranchName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(AdaptersConstants.UPDATE_NAME_ENDPOINT)
    public ResponseEntity<Void> updateProductName(@RequestParam String currentName,  @RequestParam String newName){
        productServicePort.updateName(currentName, newName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(AdaptersConstants.UPDATE_STOCK_ENDPOINT)
    public ResponseEntity<Void> updateStock(@RequestParam String productName, @RequestParam String branchName, @RequestParam Integer stock){
        productServicePort.updateStock(productName, stock, branchName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
