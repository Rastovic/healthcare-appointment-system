package com.main.app.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PharmacyMedicineDTO {
    private Long id;
    private Long pharmacyId;
    private Long medicineId;
    private String medicineName;  // optional convenience
    private int stockQuantity;
    private BigDecimal price;
}

