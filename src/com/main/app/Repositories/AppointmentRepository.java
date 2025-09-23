package com.main.app.Repositories;

import com.main.app.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPersonId(Long personId);  // Patient’s appointments
    List<Appointment> findByPersonIdAndStartTimeBefore(Long personId, LocalDateTime now);
    List<Appointment> findByPersonIdAndStartTimeAfter(Long personId, LocalDateTime now);

}
