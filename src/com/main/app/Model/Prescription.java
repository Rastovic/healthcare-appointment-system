package com.main.app.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long appointmentId;
    private Long patientId;
    private Long doctorId;

    private String notes;

    private String status;  // ISSUED, SENT, FILLED, CANCELLED, EXPIRED

    private LocalDateTime createdAt;
    private LocalDateTime issuedAt;

    private String signedBy;
    private String digitalSignature;

    private Integer version;
}
