package com.example.lpgmanagement.config;

import com.example.lpgmanagement.models.auth.User;
import com.example.lpgmanagement.models.employee.Employee;
import com.example.lpgmanagement.models.employee.types.Admin;
import com.example.lpgmanagement.repositories.auth.UserRepository;
import com.example.lpgmanagement.repositories.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class DefaultAdminInitializer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAdminInitializer.class);

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultAdminInitializer(
            UserRepository userRepository,
            EmployeeRepository employeeRepository,
            @Lazy PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Hardcoded default admin email
        String defaultAdminEmail = "deepak01962@gmail.com";

        // Check if default admin already exists
        if (!userRepository.existsByUsername(defaultAdminEmail)) {
            logger.info("Creating default admin user with email: {}", defaultAdminEmail);

            // Create Admin User
            User adminUser = createAdminUser(defaultAdminEmail);
            logger.info("Created admin user with roles: {}", adminUser.getRoles());

            // Create Admin Employee
            createAdminEmployee(adminUser);

            // Verify roles after transaction
            User savedUser = userRepository.findByUsername(defaultAdminEmail).orElse(null);
            if (savedUser != null) {
                logger.info("Verified saved admin user roles: {}", savedUser.getRoles());
            }
        }
    }

    private User createAdminUser(String email) {
        User admin = new User();
        admin.setUsername(email);
        admin.setPassword(passwordEncoder.encode("defaultadmin"));

        // Initialize roles set
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_Admin");  // Note: UserPrincipal will add ROLE_ prefix
        admin.setRoles(roles);

        logger.info("Setting roles for admin user: {}", roles);

        User savedAdmin = userRepository.save(admin);
        logger.info("Saved admin user with roles: {}", savedAdmin.getRoles());

        return savedAdmin;
    }

    private void createAdminEmployee(User adminUser) {
        Admin adminEmployee = new Admin();
        adminEmployee.setUsername(adminUser.getUsername());
        adminEmployee.setEmail(adminUser.getUsername());
        adminEmployee.setName("Deepak Kumar");
        adminEmployee.setDesignation("Administrator");
        adminEmployee.setCareof("Deo Lal Sah");
        adminEmployee.setDistrict("East Champaran");
        adminEmployee.setPanchayat("Manikpur");
        adminEmployee.setVillage("Hasuwaha");
        adminEmployee.setWardNo(3L);
        adminEmployee.setMobile(7323981004L);
        adminEmployee.setAadharNumber(244142147943L);
        adminEmployee.setEmployeeCode("EMP-ADMIN-" + UUID.randomUUID().toString().substring(0, 8));

        // Set roles explicitly
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_Admin");
        adminEmployee.setRoles(roles);

        employeeRepository.save(adminEmployee);
    }
}