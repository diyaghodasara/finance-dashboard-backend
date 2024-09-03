package com.project.finanacedashboardbackend.rest.mapper.entityToDto;

import com.project.finanacedashboardbackend.rest.dto.LoginRequest;
import com.project.finanacedashboardbackend.rest.dto.LoginResponse;
import com.project.finanacedashboardbackend.rest.entity.LoginRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginRequestMapper {
    LoginRequestEntity toEntity(LoginRequest loginRequest);
}
