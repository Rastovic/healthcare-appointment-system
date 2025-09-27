package com.main.app.Dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDTO {
    @JsonSerialize
    private Long id;
    @JsonSerialize
    private Long appointmentId;
    @JsonSerialize
    private Long patientId;
    @JsonSerialize
    private Long doctorId;
    private String notes;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime issuedAt;
    private String signedBy;
    private String digitalSignature;
    private Integer version;

    private List<PrescriptionItemDTO> items = new ArrayList<>(); // never null
}
