package com.main.app.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Entity
@Data
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
