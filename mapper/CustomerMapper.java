package com.example.lpgmanagement.mapper;

import com.example.lpgmanagement.dto.customer.CustomerCreateRequest;
import com.example.lpgmanagement.dto.customer.CustomerResponse;
import com.example.lpgmanagement.models.customer.UjjwalaImportMasterDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "consumerNumber", source = "consumerNumber")
//    @Mapping(target = "mobile", source = "mobile")
    CustomerResponse toResponse(UjjwalaImportMasterDetails customer);

    UjjwalaImportMasterDetails toEntity(CustomerCreateRequest request);
}