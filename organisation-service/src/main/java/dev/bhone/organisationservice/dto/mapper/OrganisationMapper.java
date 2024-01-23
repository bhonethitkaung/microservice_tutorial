package dev.bhone.organisationservice.dto.mapper;

import dev.bhone.organisationservice.dto.OrganisationDto;
import dev.bhone.organisationservice.entity.OrganisationEntity;

public class OrganisationMapper {

    public static OrganisationEntity mapToEntity(OrganisationDto dto) {
        return OrganisationEntity.builder()
                .id(dto.getId())
                .organisationName(dto.getOrganisationName())
                .organisationDescription(dto.getOrganisationDescription())
                .organisationCode(dto.getOrganisationCode())
                .createdDate(dto.getCreatedDate())
                .build();
    }

    public static OrganisationDto mapToDto(OrganisationEntity entity) {
        return OrganisationDto.builder()
                .id(entity.getId())
                .organisationName(entity.getOrganisationName())
                .organisationDescription(entity.getOrganisationDescription())
                .organisationCode(entity.getOrganisationCode())
                .createdDate(entity.getCreatedDate())
                .build();

    }
}
