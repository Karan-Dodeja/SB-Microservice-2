package com.organizationservice.service;

import com.organizationservice.dto.OrganizationDto;
import com.organizationservice.entity.Organization;
import com.organizationservice.mapper.OrganizationMapper;
import com.organizationservice.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImplementation implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String OrganizationCode) {
        Organization organization = organizationRepository.findByorganizationCode(OrganizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }


}
