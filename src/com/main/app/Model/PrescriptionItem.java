package com.main.app.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prescription_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prescriptionId;

    private String medicineName;
    private String form;        // tablet/capsule/liquid
    private String strength;    // "500mg"
    private String dose;        // "1 tablet"
    private String frequency;   // "2x/day"
    private String duration;    // "7 days"
    private String notes;
}

