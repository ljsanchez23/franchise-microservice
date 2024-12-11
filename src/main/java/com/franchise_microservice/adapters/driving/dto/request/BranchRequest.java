package com.franchise_microservice.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchRequest {
    private String name;
    private String franchiseName;
    private List<ProductRequest> products;
}
