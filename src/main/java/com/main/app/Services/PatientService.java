package com.main.app.Services;


import com.main.app.Dto.PatientDto;
import com.main.app.Model.Patient;
import com.main.app.Model.User;
import com.main.app.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatientProfile(PatientDto patientDto, User user) {
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setAddress(patientDto.getAddress());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        // Map other patient-specific fields from patientDto
        return patientRepository.save(patient);
    }



    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);

    }


    public Patient findByUserId(Long userId) {

        return patientRepository.findByUserId(userId);

    }

}
