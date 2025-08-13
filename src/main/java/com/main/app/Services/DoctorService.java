package com.main.app.Services;

import com.main.app.Dto.DoctorDto;
import com.main.app.Model.Doctor;
import com.main.app.Repositories.DoctorRepository;
import com.main.app.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor createDoctorProfile(DoctorDto doctorDto, User user) {
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setSpecialty(doctorDto.getSpecialty());
        // You can add other doctor-specific fields here
        return doctorRepository.save(doctor);
    }


    public List<Doctor> findBySpecialty(String specialty) {

        return doctorRepository.findBySpecialty(specialty);

    }


    public Optional<Doctor> findById(Long doctorId){
     return doctorRepository.findById(doctorId);
    }
}
