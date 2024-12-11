package com.franchise_microservice.adapters.driving.controller;

import com.franchise_microservice.adapters.util.AdaptersConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdaptersConstants.HEALTH_CONTROLLER_URL)
@RequiredArgsConstructor
public class HealthController {
    @GetMapping
    public ResponseEntity<Void> healthCheck(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
