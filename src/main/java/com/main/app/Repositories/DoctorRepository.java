package com.main.app.Repositories;

import com.main.app.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialty(String specialty);

    Optional<Doctor> findById(Long doctorId);

    Doctor save(Doctor doctor);
}
