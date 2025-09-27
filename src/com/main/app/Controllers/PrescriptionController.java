package com.main.app.Controllers;


import com.main.app.Dto.PrescriptionDTO;
import com.main.app.Services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    public PrescriptionDTO createPrescription(@RequestBody PrescriptionDTO dto) {
        return prescriptionService.createPrescription(dto);
    }

    @GetMapping("/{id}")
    public PrescriptionDTO getPrescription(@PathVariable Long id) {
        return prescriptionService.getPrescription(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<PrescriptionDTO> getPrescriptionsByPatient(@PathVariable Long patientId) {
        return prescriptionService.getPrescriptionsByPatient(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<PrescriptionDTO> getPrescriptionsByDoctor(@PathVariable Long doctorId) {
        return prescriptionService.getPrescriptionsByDoctor(doctorId);
    }
}
