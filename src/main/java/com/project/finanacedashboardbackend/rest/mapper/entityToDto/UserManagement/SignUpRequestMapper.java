package com.project.finanacedashboardbackend.rest.mapper.entityToDto.UserManagement;

import com.project.finanacedashboardbackend.rest.dto.UserManagement.SignUpRequest;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.SignUpRequestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignUpRequestMapper {
    @Mapping(target = "hashedPassword", ignore = true)
    SignUpRequestEntity toEntity(SignUpRequest signUpRequest);
}
