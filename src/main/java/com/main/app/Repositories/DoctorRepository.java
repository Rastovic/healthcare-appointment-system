package com.main.app.Repositories;

import com.main.app.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialty(String specialty);

}
