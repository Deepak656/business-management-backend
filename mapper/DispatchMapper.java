package com.example.lpgmanagement.mapper;

import com.example.lpgmanagement.dto.dispatch.*;
import com.example.lpgmanagement.models.dispatch.UjjwalaDispatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DispatchMapper {

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "invoiceStatus", constant = "MECHANIC_ASSIGNED")
    UjjwalaDispatch toAssignMechanicEntity(AssignMechanicRequest request);

//    @Mapping(target = "invoiceEditable", ignore = true)
    AssignMechanicResponse toMechanicResponse(UjjwalaDispatch disptach);

    ConsumerNumberResponse toConsumerNumberResponse(UjjwalaDispatch dispatch);
}