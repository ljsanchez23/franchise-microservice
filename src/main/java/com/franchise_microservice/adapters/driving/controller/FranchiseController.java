package com.franchise_microservice.adapters.driving.controller;

import com.franchise_microservice.adapters.driving.dto.request.FranchiseRequest;
import com.franchise_microservice.adapters.driving.mapper.request.IFranchiseRequestMapper;
import com.franchise_microservice.adapters.util.AdaptersConstants;
import com.franchise_microservice.domain.model.Product;
import com.franchise_microservice.domain.service.IFranchiseServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AdaptersConstants.FRANCHISE_CONTROLLER_URL)
@RequiredArgsConstructor
public class FranchiseController {
    private final IFranchiseServicePort franchiseServicePort;
    private final IFranchiseRequestMapper franchiseRequestMapper;

    @PostMapping(AdaptersConstants.CREATE_ENDPOINT)
    public ResponseEntity<Void> saveFranchise(@RequestBody FranchiseRequest franchiseRequest){
        franchiseServicePort.saveFranchise(franchiseRequestMapper.toModel(franchiseRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(AdaptersConstants.UPDATE_NAME_ENDPOINT)
    public ResponseEntity<Void> updateFranchiseName(@RequestParam String currentName, @RequestParam String newName){
        franchiseServicePort.updateName(currentName, newName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(AdaptersConstants.GET_HIGHEST_STOCK_ENDPOINT)
    public ResponseEntity<List<Product>> getHighestStock(@RequestParam String franchiseName){
        return ResponseEntity.ok(franchiseServicePort.listHighestStock(franchiseName));
    }
}
