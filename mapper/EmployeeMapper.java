package com.example.lpgmanagement.mapper;

import com.example.lpgmanagement.dto.employee.*;
import com.example.lpgmanagement.models.employee.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

//    @Mapping(target = "id", ignore = true)
    Employee toEntity(EmployeeCreateRequest request);

    EmployeeResponse toResponse(Employee employee);

    EmployeeResponse updateEntity(EmployeeUpdateRequest request);
}