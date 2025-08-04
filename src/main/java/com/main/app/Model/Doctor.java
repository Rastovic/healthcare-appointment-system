package com.main.app.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String specialty;

    @OneToOne
    private User user;


    public Doctor(Long doctorId) {
        super();
        setId(doctorId);
    }
}
