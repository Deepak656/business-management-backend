// src/main/java/com/example/lpgmanagement/models/base/Person.java

package com.example.lpgmanagement.models.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class Person extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String careof;

    @Column(nullable = false)
    private String village;

    @Column(nullable = false)
    private Long wardNo;

    @Column(nullable = false)
    private String panchayat;

    @Column(nullable = false)
    private String district;

    private Long mobile;
}