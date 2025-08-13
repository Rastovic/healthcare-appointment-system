package com.main.app.Services;

import com.main.app.Model.Doctor;
import com.main.app.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    public List<Doctor> findBySpecialty(String specialty) {

        return doctorRepository.findBySpecialty(specialty);

    }

    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Optional<Doctor> findById(Long doctorId){
     return doctorRepository.findById(doctorId);
    }
}
