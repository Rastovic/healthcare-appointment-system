package com.main.app.Services;

import com.main.app.Dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {

    AppointmentDto createAppointment(AppointmentDto appointmentDto);
    AppointmentDto getAppointmentById(Long id);
    List<AppointmentDto> getAllAppointments();
    AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto);
    void deleteAppointment(Long id);
    List<AppointmentDto> getAppointmentsByDoctorId(Long doctorId);
}