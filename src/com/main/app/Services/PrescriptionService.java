package com.main.app.Services;

import com.main.app.Dto.PrescriptionDTO;
import com.main.app.Mappers.PrescriptionMapper;
import com.main.app.Model.Prescription;
import com.main.app.Model.PrescriptionItem;
import com.main.app.Repositories.PrescriptionItemRepository;
import com.main.app.Repositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionItemRepository itemRepository;



    // Create new prescription
    public PrescriptionDTO createPrescription(PrescriptionDTO dto) {
        Prescription entity = PrescriptionMapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus("ISSUED");

        Prescription saved = prescriptionRepository.save(entity);

        // Save items
        List<PrescriptionItem> items = dto.getItems().stream()
                .map(PrescriptionMapper::toItemEntity)
                .peek(item -> item.setPrescriptionId(saved.getId()))
                .map(itemRepository::save)
                .collect(Collectors.toList());

        return PrescriptionMapper.toDTO(saved, items);
    }

    // Get by ID
    public PrescriptionDTO getPrescription(Long id) {
        Prescription entity = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        List<PrescriptionItem> items = itemRepository.findByPrescriptionId(id);

        return PrescriptionMapper.toDTO(entity, items);
    }

    // Get all for a patient
    public List<PrescriptionDTO> getPrescriptionsByPatient(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId).stream()
                .map(p -> {
                    List<PrescriptionItem> items = itemRepository.findByPrescriptionId(p.getId());
                    return PrescriptionMapper.toDTO(p, items);
                })
                .collect(Collectors.toList());
    }

    // Get all for a doctor
    public List<PrescriptionDTO> getPrescriptionsByDoctor(Long doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId).stream()
                .map(p -> {
                    List<PrescriptionItem> items = itemRepository.findByPrescriptionId(p.getId());
                    return PrescriptionMapper.toDTO(p, items);
                })
                .collect(Collectors.toList());
    }
}
