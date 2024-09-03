package com.project.finanacedashboardbackend.rest.mapper.entityToDto;

import com.project.finanacedashboardbackend.rest.dto.SignUpRequest;
import com.project.finanacedashboardbackend.rest.entity.SignUpRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SignUpRequestMapper {
    SignUpRequestEntity toEntity(SignUpRequest signUpRequest);
}
