package com.main.app.Services;

import com.main.app.Dto.AppointmentDto;
import com.main.app.Dto.PrescriptionDTO;
import com.main.app.Model.Appointment;
import com.main.app.Model.Person;
import com.main.app.Model.Prescription;
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
    private PrescriptionService prescriptionService;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = convertDtoToEntity(appointmentDto);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setDoctorId(appointmentDto.getDoctorId());
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertEntityToDto(savedAppointment);
    }

    @Override
    public AppointmentDto getAppointmentById(Long id) {
        // Get the appointment safely
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isEmpty()) {
            return null; // or throw a custom exception if you prefer
        }

        Appointment appointment = appointmentOptional.get();

        // Get prescription and set it if found
        PrescriptionDTO prescription = prescriptionService.getPrescriptionByAppointmentId(id);
        if (prescription != null) {
            appointment.setPrescriptionId(prescription.getId());
        }

        // Convert to DTO
        return convertEntityToDto(appointment);
    }


    @Override
    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndStartTimeAfter(doctorId, LocalDateTime.now());
        return appointments.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPersonId(patientId);
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
            appointment.setPrescriptionId(appointmentDto.getPrescriptionId());

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
        appointmentDto.setPrescriptionId(appointment.getPrescriptionId());
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
        appointment.setPrescriptionId(appointmentDto.getPrescriptionId());

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


    @Override
    public List<AppointmentDto> getPreviousAppointments(Long personId) {
        List<Appointment> appointments =  appointmentRepository.findByPersonIdAndStartTimeBefore(personId, LocalDateTime.now());
        return appointments.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getUpcomingAppointments(Long personId) {
        List<Appointment> appointments =  appointmentRepository.findByPersonIdAndStartTimeAfter(personId, LocalDateTime.now());
        return appointments.stream().map(this::convertEntityToDto).collect(Collectors.toList());

    }

}