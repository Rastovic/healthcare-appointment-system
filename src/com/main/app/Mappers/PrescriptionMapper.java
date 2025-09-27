package com.main.app.Mappers;


import com.main.app.Dto.PharmacyOrderDTO;
import com.main.app.Dto.PrescriptionDTO;
import com.main.app.Dto.PrescriptionItemDTO;
import com.main.app.Model.PharmacyOrder;
import com.main.app.Model.Prescription;
import com.main.app.Model.PrescriptionItem;

import java.util.List;
import java.util.stream.Collectors;

public class PrescriptionMapper {

    // Prescription -> DTO
    public static PrescriptionDTO toDTO(Prescription entity, List<PrescriptionItem> items) {
        return PrescriptionDTO.builder()
                .id(entity.getId())
                .appointmentId(entity.getAppointmentId())
                .patientId(entity.getPatientId())
                .doctorId(entity.getDoctorId())
                .notes(entity.getNotes())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .issuedAt(entity.getIssuedAt())
                .signedBy(entity.getSignedBy())
                .digitalSignature(entity.getDigitalSignature())
                .version(entity.getVersion())
                .items(items != null ? items.stream().map(PrescriptionMapper::toItemDTO).collect(Collectors.toList()) : null)
                .build();
    }

    // DTO -> Prescription
    public static Prescription toEntity(PrescriptionDTO dto) {
        return Prescription.builder()
                .id(dto.getId())
                .appointmentId(dto.getAppointmentId())
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .notes(dto.getNotes())
                .status(dto.getStatus())
                .createdAt(dto.getCreatedAt())
                .issuedAt(dto.getIssuedAt())
                .signedBy(dto.getSignedBy())
                .digitalSignature(dto.getDigitalSignature())
                .version(dto.getVersion())
                .build();
    }

    // Item -> DTO
    public static PrescriptionItemDTO toItemDTO(PrescriptionItem entity) {
        return PrescriptionItemDTO.builder()
                .id(entity.getId())
                .prescriptionId(entity.getPrescriptionId())
                .medicineName(entity.getMedicineName())
                .form(entity.getForm())
                .strength(entity.getStrength())
                .dose(entity.getDose())
                .frequency(entity.getFrequency())
                .duration(entity.getDuration())
                .notes(entity.getNotes())
                .build();
    }

    // DTO -> Item
    public static PrescriptionItem toItemEntity(PrescriptionItemDTO dto) {
        return PrescriptionItem.builder()
                .id(dto.getId())
                .prescriptionId(dto.getPrescriptionId())
                .medicineName(dto.getMedicineName())
                .form(dto.getForm())
                .strength(dto.getStrength())
                .dose(dto.getDose())
                .frequency(dto.getFrequency())
                .duration(dto.getDuration())
                .notes(dto.getNotes())
                .build();
    }

    // Pharmacy Order -> DTO
    public static PharmacyOrderDTO toOrderDTO(PharmacyOrder entity) {
        return PharmacyOrderDTO.builder()
                .id(entity.getId())
                .prescriptionId(entity.getPrescriptionId())
                .pharmacyId(entity.getPharmacyId())
                .externalOrderId(entity.getExternalOrderId())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    // DTO -> Order
    public static PharmacyOrder toOrderEntity(PharmacyOrderDTO dto) {
        return PharmacyOrder.builder()
                .id(dto.getId())
                .prescriptionId(dto.getPrescriptionId())
                .pharmacyId(dto.getPharmacyId())
                .externalOrderId(dto.getExternalOrderId())
                .status(dto.getStatus())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}

