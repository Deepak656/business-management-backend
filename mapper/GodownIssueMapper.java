package com.example.lpgmanagement.mapper;

import com.example.lpgmanagement.dto.godownissue.GodownIssueRequest;
import com.example.lpgmanagement.dto.godownissue.GodownIssueResponse;
import com.example.lpgmanagement.models.dispatch.UjjwalaDispatch;
import org.mapstruct.Mapping;

public interface GodownIssueMapper {
//    @Mapping(target = "productIssuedFrom",  source = "productIssuedFrom")
//    @Mapping(target = "mechanicAadharDuringGodown",  source = "mechanicAadharDuringGodown")
//    @Mapping(target = "godownIssuedByName",  source = "godownIssuedByName")
//    @Mapping(target = "godownIssuedById",  source = "godownIssuedById")
//    @Mapping(target = "godownIssueDate",  source = "godownIssueDate")
//    @Mapping(target = "godownStatus", source = "godownStatus")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "consumerNumber", ignore = true)
    @Mapping(target = "aadharNumber", ignore = true)
    @Mapping(target = "mechanicAssignedId", ignore = true)
    @Mapping(target = "mechanicAssignedName", ignore = true)
    @Mapping(target = "invoiceStatus", ignore = true)
    @Mapping(target = "invoiceEditable", ignore = true)
    @Mapping(target = "aadharNumberDuringInvoice", ignore = true)
    @Mapping(target = "customerMobileDuringInvoice", ignore = true)
    @Mapping(target = "customerWardNoDuringInvoice", ignore = true)
    @Mapping(target = "dispatchTo", ignore = true)
    @Mapping(target = "godownEditable",  ignore = true)
    @Mapping(target = "ucode", ignore = true)
    @Mapping(target = "invoiceNumber", ignore = true)
    @Mapping(target = "invoiceDate", ignore = true)
    @Mapping(target = "invoiceIssuedByName", ignore = true)
    @Mapping(target = "invoiceIssuedById", ignore = true)
    GodownIssueResponse toGodownIssueEntity(GodownIssueRequest request);
//    @Mapping(target = "consumerNumber", source = "consumerNumber")
//    @Mapping(target = "godownStatus", source = "godownStatus")
//    @Mapping(target = "invoiceDate", source = "invoiceDate")
//    @Mapping(target = "invoiceIssuedByName", source = "invoiceIssuedByName")
//    @Mapping(target = "invoiceIssuedById", source = "invoiceIssuedById")
//    @Mapping(source = "godownIssueDate", target = "godownIssueDate")
//    @Mapping(source = "godownIssuedByName", target = "godownIssuedByName")
//    @Mapping(source = "godownIssuedById", target = "godownIssuedById")
//    @Mapping(source = "mechanicAssignedId", target = "mechanicAssignedId")
//    @Mapping(source = "mechanicAssignedName", target = "mechanicAssignedName")
//    @Mapping(source = "productIssuedFrom", target = "productIssuedFrom")
    GodownIssueResponse toGodownResponse(UjjwalaDispatch dispatch);
}
