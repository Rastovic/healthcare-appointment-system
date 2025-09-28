package com.main.app.Mappers;


import com.main.app.Dto.PharmacyMedicineDTO;
import com.main.app.Model.PharmacyMedicine;

public class PharmacyMedicineMapper {

    public static PharmacyMedicineDTO toDto(PharmacyMedicine entity) {
        if (entity == null) return null;
        PharmacyMedicineDTO dto = new PharmacyMedicineDTO();
        dto.setId(entity.getId());
        dto.setPharmacyId(entity.getPharmacyId());
        dto.setMedicineId(entity.getMedicineId());
        dto.setMedicineName(entity.getMedicineName());
        dto.setStockQuantity(entity.getStockQuantity());
        dto.setPrice(entity.getPrice());
        // If you want medicineName, you must join with Medicine entity or service
        return dto;
    }

    public static PharmacyMedicine toEntity(PharmacyMedicineDTO dto) {
        if (dto == null) return null;
        return PharmacyMedicine.builder()
                .id(dto.getId())
                .pharmacyId(dto.getPharmacyId())
                .medicineId(dto.getMedicineId())
                .medicineName(dto.getMedicineName())
                .stockQuantity(dto.getStockQuantity())
                .price(dto.getPrice())
                .build();
    }
}
