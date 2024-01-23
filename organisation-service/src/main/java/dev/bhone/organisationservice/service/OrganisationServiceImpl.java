package dev.bhone.organisationservice.service;

import dev.bhone.organisationservice.dto.OrganisationDto;
import dev.bhone.organisationservice.dto.mapper.OrganisationMapper;
import dev.bhone.organisationservice.entity.OrganisationEntity;
import dev.bhone.organisationservice.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationServiceImpl implements OrganisationService{

    private final OrganisationRepository repository;

    @Autowired
    public OrganisationServiceImpl(OrganisationRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrganisationDto saveOrganisation(OrganisationDto dto) {
        OrganisationEntity savedEntity = repository.save(OrganisationMapper.mapToEntity(dto));
        return OrganisationMapper.mapToDto(savedEntity);
    }

    @Override
    public OrganisationDto findByOrganisationCode(String organisationCode) throws Exception {
        OrganisationEntity entity = repository.findByOrganisationCode(organisationCode).orElseThrow(() -> new Exception("Not Found"));
        return OrganisationMapper.mapToDto(entity);
    }
}
