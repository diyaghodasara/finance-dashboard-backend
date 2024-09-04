package com.project.finanacedashboardbackend.rest.mapper.entityToDto;

import com.project.finanacedashboardbackend.rest.dto.LoginResponse;
import com.project.finanacedashboardbackend.rest.entity.LoginResponseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginResponseMapper {
    LoginResponse toDto(LoginResponseEntity loginResponseEntity);
}
