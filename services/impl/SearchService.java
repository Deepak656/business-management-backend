//package com.example.lpgmanagement.services.impl;
//
//import com.example.lpgmanagement.models.customer.UjjwalaImportMaster;
//import com.example.lpgmanagement.models.dispatch.UjjwalaDispatch;
//import com.example.lpgmanagement.models.employee.Employee;
//import com.example.lpgmanagement.repositories.customer.CustomerRepository;
//import com.example.lpgmanagement.repositories.dispatch.UjjwalaDispatchRepository;
//import com.example.lpgmanagement.repositories.employee.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SearchService {
//    @Autowired
//    private EmployeeRepository employeeRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Autowired
//    private UjjwalaDispatchRepository dispatchRepository;
//
//    public List<Employee> searchEmployees(String query) {
//        return employeeRepository.findByNameContainingOrEmployeeCodeContaining(query, query);
//    }
//
//    public List<UjjwalaImportMaster> searchCustomers(String query) {
//        return customerRepository.findByNameContainingOrConsumerNumberContaining(query, query);
//    }
//
////    public List<UjjwalaDispatch> searchDispatches(String query) {
////        return dispatchRepository.findByConsumerNumberContainingOrNameContaining(query, query);
////    }
//}