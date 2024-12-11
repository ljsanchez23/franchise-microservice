package com.franchise_microservice.adapters.driven.jpa.mysql.adapter;

import com.franchise_microservice.adapters.driven.jpa.mysql.entity.FranchiseEntity;
import com.franchise_microservice.adapters.driven.jpa.mysql.mapper.IFranchiseEntityMapper;
import com.franchise_microservice.adapters.driven.jpa.mysql.repository.IFranchiseRepository;
import com.franchise_microservice.domain.model.Franchise;
import com.franchise_microservice.domain.ports.IFranchisePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FranchiseAdapter implements IFranchisePersistencePort {
    private final IFranchiseRepository franchiseRepository;
    private final IFranchiseEntityMapper franchiseEntityMapper;

    @Override
    public Optional<Franchise> findByName(String name) {
        return franchiseRepository.findByName(name)
                .map(franchiseEntityMapper::toModel);
    }

    @Override
    public void saveFranchise(Franchise franchise){
        Optional<FranchiseEntity> existingFranchise = franchiseRepository.findByName(franchise.getName());
        existingFranchise.ifPresentOrElse(
                existingEntity -> {
                    existingEntity.setName(franchise.getName());
                    franchiseRepository.save(existingEntity);
                },
                () -> franchiseRepository.save(franchiseEntityMapper.toEntity(franchise))
        );
    }

    @Override
    public void updateName(String currentName, String newName){
        franchiseRepository.updateName(currentName, newName);
    }
}
