//package com.example.lpgmanagement.mapper;
//
//        import com.example.lpgmanagement.dto.delivery.DeliveryConfirmRequest;
//        import com.example.lpgmanagement.dto.delivery.DeliveryConfirmResponse;
//        import com.example.lpgmanagement.dto.dispatch.*;
//        import com.example.lpgmanagement.models.delivery.DeliveryConfirm;
//        import com.example.lpgmanagement.models.dispatch.UjjwalaDispatch;
//        import org.mapstruct.Mapper;
//        import org.mapstruct.Mapping;
//        import org.mapstruct.NullValuePropertyMappingStrategy;
//
//@Mapper(componentModel = "spring",
//        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//public interface DeliveryMapper {
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "consumerNumber", source = "consumerNumber")
////    @Mapping(source = "godownStatus", target = "godown_status")
//    @Mapping(source = "mechanicAssignedId", target = "mechanicAssignedId")
//    @Mapping(source = "mechanicAssignedName", target = "mechanicAssignedName")
//    @Mapping(source = "actualDeliveryByName", target = "actualDeliveryByName")
//    @Mapping(source = "actualDeliveryById", target  = "actualDeliveryById")
//    @Mapping(source = "customerMobileByDeliveryBoy", target  = "customerMobileByDeliveryBoy")
//    @Mapping(source = "customerWardNoByDeliveryBoy", target  = "customerWardNoByDeliveryBoy")
//    @Mapping(source = "aadharNumberByDeliveryBoy", target  = "aadharNumberByDeliveryBoy")
//    @Mapping(source = "deliveredTo", target = "deliveredTo")
//    @Mapping(source = "deliveredToAadhar", target = "deliveredToAadhar")
//    @Mapping(source = "deliveredToName", target = "deliveredToName")
//    @Mapping(source = "deliveredToFatherName", target = "deliveredToFatherName")
//    @Mapping(source = "actualDeliveryDate", target = "actualDeliveryDate")
////    @Mapping(target = "invoiceStatus", ignore = true)
////    @Mapping(target = "godownStatus", ignore = true)
//    DeliveryConfirm toDeliveryConfirmEntity(DeliveryConfirmRequest request);
//
//    @Mapping(target = "consumerNumber", source = "consumerNumber")
//    @Mapping(target = "name", source = "name")
//    @Mapping(source = "status", target = "status")
//    @Mapping(source = "mechanicAssignedId", target = "mechanicAssignedId")
//    @Mapping(source = "mechanicAssignedName", target = "mechanicAssignedName")
//    @Mapping(source = "customerMobileByDeliveryBoy", target  = "customerMobileByDeliveryBoy")
//    @Mapping(source = "customerWardNoByDeliveryBoy", target  = "customerWardNoByDeliveryBoy")
//    @Mapping(source =  "aadharNumberByDeliveryBoy", target  = "aadharNumberByDeliveryBoy")
//    @Mapping(source =  "deliveredTo", target = "deliveredTo")
//    @Mapping(source = "deliveredToAadhar", target = "deliveredToAadhar")
//    @Mapping(source = "deliveredToName", target = "deliveredToName")
//    @Mapping(source =  "deliveredToFatherName", target = "deliveredToFatherName")
//    @Mapping(source =  "actualDeliveryDate", target = "actualDeliveryDate")
//    DeliveryConfirmResponse toDeliveryConfirmResponse(DeliveryConfirm deliveryconfirm);
//
//}