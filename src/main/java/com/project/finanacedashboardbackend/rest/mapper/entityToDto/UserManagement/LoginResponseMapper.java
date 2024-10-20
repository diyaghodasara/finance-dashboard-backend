package com.project.finanacedashboardbackend.rest.mapper.entityToDto.UserManagement;

import com.project.finanacedashboardbackend.rest.dto.UserManagement.LoginResponse;
import com.project.finanacedashboardbackend.rest.entity.UserManagement.LoginResponseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginResponseMapper {
    LoginResponse toDto(LoginResponseEntity loginResponseEntity);
}
