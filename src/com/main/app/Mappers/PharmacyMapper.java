package com.main.app.Mappers;


import com.main.app.Dto.PharmacyDTO;
import com.main.app.Model.Pharmacy;

public class PharmacyMapper {

    public static PharmacyDTO toDto(Pharmacy entity) {
        if (entity == null) return null;
        return PharmacyDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .build();
    }

    public static Pharmacy toEntity(PharmacyDTO dto) {
        if (dto == null) return null;
        return Pharmacy.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .build();
    }
}

