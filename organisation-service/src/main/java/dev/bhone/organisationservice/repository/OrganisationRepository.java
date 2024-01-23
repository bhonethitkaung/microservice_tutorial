package dev.bhone.organisationservice.repository;

import dev.bhone.organisationservice.entity.OrganisationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<OrganisationEntity, Long> {

    Optional<OrganisationEntity> findByOrganisationCode(String organisationCode);
}
