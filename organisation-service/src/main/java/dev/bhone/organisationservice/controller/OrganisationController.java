package dev.bhone.organisationservice.controller;

import dev.bhone.organisationservice.dto.OrganisationDto;
import dev.bhone.organisationservice.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("organisations")
public class OrganisationController {

    private final OrganisationService service;

    @Autowired
    public OrganisationController(OrganisationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrganisationDto> saveOrganisation(@RequestBody OrganisationDto dto) {
        OrganisationDto savedDto = service.saveOrganisation(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<OrganisationDto> getByOrganisationCode(@PathVariable("code") String organisationCode) throws Exception {
        OrganisationDto foundDto = service.findByOrganisationCode(organisationCode);
        return new ResponseEntity<>(foundDto, HttpStatus.OK);
    }
}
