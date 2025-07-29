package com.main.app.Repositories;

import com.main.app.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByUserId(Long userId);

}