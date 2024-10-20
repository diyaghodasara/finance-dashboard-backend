package com.project.finanacedashboardbackend.rest.mapper.entityToDto.UserManagement;

import com.project.finanacedashboardbackend.rest.dto.UserManagement.SignUpRequest;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.SignUpRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SignUpRequestMapper {
    SignUpRequestEntity toEntity(SignUpRequest signUpRequest);
}
