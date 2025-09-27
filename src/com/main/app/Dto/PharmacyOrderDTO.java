package com.main.app.Dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyOrderDTO {
    @JsonSerialize
    private Long id;
    @JsonSerialize
    private Long prescriptionId;
    @JsonSerialize
    private Long pharmacyId;
    @JsonSerialize
    private String externalOrderId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
