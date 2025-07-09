package com.example.lpgmanagement.services.impl;

import com.example.lpgmanagement.models.dispatch.UjjwalaDispatch;
import com.example.lpgmanagement.repositories.dispatch.UjjwalaDispatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    @Autowired
    private UjjwalaDispatchRepository ujjwalaDispatchRepository;

//    public Map<String, Long> getDispatchesByDistrict(LocalDate startDate, LocalDate endDate) {
//        List<UjjwalaDispatch> dispatches = ujjwalaDispatchRepository.findByInvoiceDateBetween(startDate, endDate);
//        return dispatches.stream()
//                .collect(Collectors.groupingBy(UjjwalaDispatch::getDistrict, Collectors.counting()));
//    }

//    public Map<String, Long> getDispatchesByMechanic(LocalDate startDate, LocalDate endDate) {
//        List<UjjwalaDispatch> dispatches = ujjwalaDispatchRepository.findByMechanicDeliveryDateBetween(startDate, endDate);
//        return dispatches.stream()
//                .collect(Collectors.groupingBy(UjjwalaDispatch::getMechanicName, Collectors.counting()));
//    }

//    public long getAverageDeliveryTime(LocalDate startDate, LocalDate endDate) {
//        List<UjjwalaDispatch> dispatches = ujjwalaDispatchRepository.findByInvoiceDateBetween(startDate, endDate);
//        return dispatches.stream()
//                .filter(d -> d.getMechanicDeliveryDate() != null)
//                .mapToLong(d -> ChronoUnit.DAYS.between(d.getInvoiceDate(), d.getMechanicDeliveryDate()))
//                .average()
//                .orElse(0);
//    }
}