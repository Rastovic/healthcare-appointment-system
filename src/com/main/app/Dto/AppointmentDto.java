package com.main.app.Dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {

    @JsonSerialize
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private String location;
    private Long personId; // Represents the patient
    private Long doctorId; // Represents the doctor
    private String testResults;
    private String doctorNotes;
    private String prescription;

}