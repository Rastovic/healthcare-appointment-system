package com.main.app.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pharmacy_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prescriptionId;
    private Long pharmacyId;

    private String externalOrderId;
    private String status; // PENDING, SUBMITTED, READY, FAILED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

