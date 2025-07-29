package com.main.app.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import jakarta.persistence.Id;


@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role; // PATIENT, DOCTOR, ADMIN

    private String email;

}