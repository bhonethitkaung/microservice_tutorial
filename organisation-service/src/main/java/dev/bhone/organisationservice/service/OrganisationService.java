package dev.bhone.organisationservice.service;

import dev.bhone.organisationservice.dto.OrganisationDto;

public interface OrganisationService {

    OrganisationDto saveOrganisation(OrganisationDto dto);

    OrganisationDto findByOrganisationCode(String organisationCode) throws Exception;
}
