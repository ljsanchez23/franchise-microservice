package com.franchise_microservice.adapters.driving.controller;

import com.franchise_microservice.adapters.driving.dto.request.BranchRequest;
import com.franchise_microservice.adapters.driving.mapper.request.IBranchRequestMapper;
import com.franchise_microservice.adapters.util.AdaptersConstants;
import com.franchise_microservice.domain.service.IBranchServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdaptersConstants.BRANCH_CONTROLLER_URL)
@RequiredArgsConstructor
public class BranchController {
    private final IBranchServicePort branchServicePort;
    private final IBranchRequestMapper branchRequestMapper;

    @PostMapping(AdaptersConstants.CREATE_ENDPOINT)
    public ResponseEntity<Void> saveBranch(@RequestBody BranchRequest branchRequest){
        branchServicePort.saveBranch(branchRequestMapper.toModel(branchRequest), branchRequest.getFranchiseName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(AdaptersConstants.UPDATE_NAME_ENDPOINT)
    public ResponseEntity<Void> updateBranchName(@RequestParam String currentName, @RequestParam String newName){
        branchServicePort.updateName(currentName, newName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(AdaptersConstants.DELETE_PRODUCT_FROM_BRANCH_ENDPOINT)
    public ResponseEntity<Void> deleteProductFromBranch(@RequestParam String branchName,
                                                        @RequestParam String productName){
        branchServicePort.deleteProduct(branchName, productName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
