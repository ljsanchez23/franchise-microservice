package com.franchise_microservice.configuration;

import com.franchise_microservice.adapters.driven.jpa.mysql.adapter.BranchAdapter;
import com.franchise_microservice.adapters.driven.jpa.mysql.adapter.FranchiseAdapter;
import com.franchise_microservice.adapters.driven.jpa.mysql.adapter.ProductAdapter;
import com.franchise_microservice.adapters.driven.jpa.mysql.mapper.IBranchEntityMapper;
import com.franchise_microservice.adapters.driven.jpa.mysql.mapper.IFranchiseEntityMapper;
import com.franchise_microservice.adapters.driven.jpa.mysql.mapper.IProductEntityMapper;
import com.franchise_microservice.adapters.driven.jpa.mysql.repository.IBranchRepository;
import com.franchise_microservice.adapters.driven.jpa.mysql.repository.IFranchiseRepository;
import com.franchise_microservice.adapters.driven.jpa.mysql.repository.IProductRepository;
import com.franchise_microservice.domain.ports.IBranchPersistencePort;
import com.franchise_microservice.domain.ports.IFranchisePersistencePort;
import com.franchise_microservice.domain.ports.IProductPersistencePort;
import com.franchise_microservice.domain.service.IBranchServicePort;
import com.franchise_microservice.domain.service.IFranchiseServicePort;
import com.franchise_microservice.domain.service.IProductServicePort;
import com.franchise_microservice.domain.service.impl.BranchUseCase;
import com.franchise_microservice.domain.service.impl.FranchiseUseCase;
import com.franchise_microservice.domain.service.impl.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IFranchiseRepository franchiseRepository;
    private final IFranchiseEntityMapper franchiseEntityMapper;
    private final IBranchRepository branchRepository;
    private final IBranchEntityMapper branchEntityMapper;
    private final IProductRepository productRepository;
    private final IProductEntityMapper productEntityMapper;

    @Bean
    public IFranchisePersistencePort franchisePersistencePort(){
        return new FranchiseAdapter(franchiseRepository, franchiseEntityMapper);
    }

    @Bean
    public IBranchPersistencePort branchPersistencePort(){
        return new BranchAdapter(branchRepository, branchEntityMapper, franchiseRepository);
    }

    @Bean
    public IProductPersistencePort productPersistencePort(){
        return new ProductAdapter(productRepository, productEntityMapper, branchRepository);
    }

    @Bean
    public IFranchiseServicePort franchiseServicePort(){
        return new FranchiseUseCase(franchisePersistencePort());
    }

    @Bean
    public IBranchServicePort branchServicePort(){
        return new BranchUseCase(branchPersistencePort(), franchisePersistencePort());
    }

    @Bean
    public IProductServicePort productServicePort(){
        return new ProductUseCase(productPersistencePort(), branchPersistencePort());
    }
}
