package com.franchise_microservice.adapters.driven.jpa.mysql.mapper;

import com.franchise_microservice.adapters.driven.jpa.mysql.entity.BranchEntity;
import com.franchise_microservice.adapters.util.AdaptersConstants;
import com.franchise_microservice.domain.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = AdaptersConstants.SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBranchEntityMapper {
    Branch toModel(BranchEntity branchEntity);
    BranchEntity toEntity(Branch branch);
}
