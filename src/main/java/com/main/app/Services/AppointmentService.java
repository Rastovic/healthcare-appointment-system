package com.main.app.Services;

import com.main.app.Model.Appointment;
import com.main.app.Repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;



    public Appointment bookAppointment(Appointment appointment) {

        return appointmentRepository.save(appointment);

    }


    public List<Appointment> findByPatientId(Long patientId) {

        return appointmentRepository.findByPatientId(patientId);

    }


    public List<Appointment> findByDoctorId(Long doctorId) {

        return appointmentRepository.findByDoctorId(doctorId);

    }


    public Appointment updateAppointment(Appointment appointment) {

        return appointmentRepository.save(appointment);

    }

    public Optional<Appointment> findById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }
}
