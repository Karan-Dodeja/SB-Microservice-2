package com.organizationservice.controller;

import com.organizationservice.dto.OrganizationDto;
import com.organizationservice.entity.Organization;
import com.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @GetMapping("{organization-code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("organization-code") String OrganizationCode) {
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(OrganizationCode);
        return ResponseEntity.ok(organizationDto);
    }

}
