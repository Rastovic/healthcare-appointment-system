package com.main.app.Dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItemDTO {
    @JsonSerialize
    private Long id;
    @JsonSerialize
    private Long prescriptionId;
    private String medicineName;
    private String form;
    private String strength;
    private String dose;
    private String frequency;
    private String duration;
    private String notes;
}

