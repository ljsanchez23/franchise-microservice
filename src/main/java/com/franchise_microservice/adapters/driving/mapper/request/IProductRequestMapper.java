package com.franchise_microservice.adapters.driving.mapper.request;

import com.franchise_microservice.adapters.driving.dto.request.ProductRequest;
import com.franchise_microservice.adapters.util.AdaptersConstants;
import com.franchise_microservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = AdaptersConstants.SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProductRequestMapper {
    Product toModel(ProductRequest productRequest);
    ProductRequest toRequest(Product product);
}