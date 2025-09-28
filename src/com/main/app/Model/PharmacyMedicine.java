package com.main.app.Model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pharmacy_medicines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pharmacyId;  // FK to pharmacies table

    @Column(nullable = false)
    private Long medicineId;  // FK to medicines table

    @Column(nullable = false)
    private int stockQuantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "medicine_name")
    private String medicineName;
}

