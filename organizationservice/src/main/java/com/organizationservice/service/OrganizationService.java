package com.organizationservice.service;

import com.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String OrganizationCode);
}
