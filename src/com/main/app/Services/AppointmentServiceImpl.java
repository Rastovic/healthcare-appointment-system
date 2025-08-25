package com.main.app.Services;

import com.main.app.Dto.AppointmentDto;
import com.main.app.Model.Appointment;
import com.main.app.Model.Person;
import com.main.app.Repositories.AppointmentRepository;
import com.main.app.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = convertDtoToEntity(appointmentDto);
        appointment.setCreatedAt(LocalDateTime.now());
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertEntityToDto(savedAppointment);
    }

    @Override
    public AppointmentDto getAppointmentById(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        return appointmentOptional.map(this::convertEntityToDto).orElse(null);
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        return appointments.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            // Update fields from DTO
            appointment.setTitle(appointmentDto.getTitle());
            appointment.setDescription(appointmentDto.getDescription());
            appointment.setStartTime(appointmentDto.getStartTime());
            appointment.setEndTime(appointmentDto.getEndTime());
            appointment.setStatus(appointmentDto.getStatus());
            appointment.setLocation(appointmentDto.getLocation());
            appointment.setTestResults(appointmentDto.getTestResults());
            appointment.setDoctorNotes(appointmentDto.getDoctorNotes());
            appointment.setPrescription(appointmentDto.getPrescription());

            // Update relationships
            if (appointmentDto.getPersonId() != null) {
                Person patient = personRepository.findById(appointmentDto.getPersonId())
                        .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + appointmentDto.getPersonId()));
                appointment.setPersonId(patient.getId());
            }
            if (appointmentDto.getDoctorId() != null) {
                Person doctor = personRepository.findById(appointmentDto.getDoctorId())
                        .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + appointmentDto.getDoctorId()));
                appointment.setDoctorId(doctor.getDoctorId());
            }

            appointment.setUpdatedAt(LocalDateTime.now());
            Appointment updatedAppointment = appointmentRepository.save(appointment);
            return convertEntityToDto(updatedAppointment);
        }
        return null; // Or throw an exception
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentDto convertEntityToDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setTitle(appointment.getTitle());
        appointmentDto.setDescription(appointment.getDescription());
appointmentDto.setStartTime(appointment.getStartTime());
        appointmentDto.setEndTime(appointment.getEndTime());
        appointmentDto.setStatus(appointment.getStatus());
        appointmentDto.setLocation(appointment.getLocation());
        appointmentDto.setCreatedAt(appointment.getCreatedAt());
        appointmentDto.setUpdatedAt(appointment.getUpdatedAt());
        if (appointment.getPersonId() != null) {
            appointmentDto.setPersonId(appointment.getPersonId());
        }

        if (appointment.getDoctorId() != null) {
            appointmentDto.setDoctorId(appointment.getDoctorId());
        }
        appointmentDto.setTestResults(appointment.getTestResults());
        appointmentDto.setDoctorNotes(appointment.getDoctorNotes());
        appointmentDto.setPrescription(appointment.getPrescription());
        return appointmentDto;
    }

    private Appointment convertDtoToEntity(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        // ID is typically generated by the database, so don't set it here for creation
        appointment.setTitle(appointmentDto.getTitle());
        appointment.setDescription(appointmentDto.getDescription());
        appointment.setStartTime(appointmentDto.getStartTime());
        appointment.setEndTime(appointmentDto.getEndTime());
        appointment.setStatus(appointmentDto.getStatus());
        appointment.setLocation(appointmentDto.getLocation());
        appointment.setTestResults(appointmentDto.getTestResults());
        appointment.setDoctorNotes(appointmentDto.getDoctorNotes());
        appointment.setPrescription(appointmentDto.getPrescription());

        if (appointmentDto.getPersonId() != null) {
            Person patient = personRepository.findById(appointmentDto.getPersonId())
                    .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + appointmentDto.getPersonId()));
            appointment.setPersonId(patient.getId());
        }
        if (appointmentDto.getDoctorId() != null) {
            Person doctor = personRepository.findById(appointmentDto.getDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + appointmentDto.getDoctorId()));
            appointment.setDoctorId(doctor.getDoctorId());
        }

        return appointment;
    }
}