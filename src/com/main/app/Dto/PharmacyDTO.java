package com.main.app.Dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyDTO {
    @JsonSerialize
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}

