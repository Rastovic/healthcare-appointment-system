package com.main.app.Services;


import com.main.app.Model.Patient;
import com.main.app.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;



    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);

    }


    public Patient findByUserId(Long userId) {

        return patientRepository.findByUserId(userId);

    }

}
