package com.main.app.Services;

import com.main.app.Model.Doctor;
import com.main.app.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    public List<Doctor> findBySpecialty(String specialty) {

        return doctorRepository.findBySpecialty(specialty);

    }

}
