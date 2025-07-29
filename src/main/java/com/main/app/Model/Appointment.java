package com.main.app.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    private LocalDateTime appointmentTime;

    private String status; // SCHEDULED, COMPLETED, CANCELED

}
