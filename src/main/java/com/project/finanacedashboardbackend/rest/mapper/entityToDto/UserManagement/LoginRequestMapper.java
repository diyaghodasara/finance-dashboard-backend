package com.project.finanacedashboardbackend.rest.mapper.entityToDto.UserManagement;

import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginRequest;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginRequestMapper {
    LoginRequestEntity toEntity(LoginRequest loginRequest);
}
