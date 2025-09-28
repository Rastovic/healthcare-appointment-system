package com.main.app.Services;


import com.main.app.Dto.PharmacyMedicineDTO;
import com.main.app.Mappers.PharmacyMedicineMapper;
import com.main.app.Model.PharmacyMedicine;
import com.main.app.Repositories.PharmacyMedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyMedicineServiceImpl implements PharmacyMedicineService {

    private final PharmacyMedicineRepository repository;

    @Override
    public PharmacyMedicineDTO create(PharmacyMedicineDTO dto) {
        PharmacyMedicine entity = PharmacyMedicineMapper.toEntity(dto);
        return PharmacyMedicineMapper.toDto(repository.save(entity));
    }

    @Override
    public PharmacyMedicineDTO update(Long id, PharmacyMedicineDTO dto) {
        PharmacyMedicine entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PharmacyMedicine not found"));
        entity.setPharmacyId(dto.getPharmacyId());
        entity.setMedicineId(dto.getMedicineId());
        entity.setStockQuantity(dto.getStockQuantity());
        entity.setPrice(dto.getPrice());
        return PharmacyMedicineMapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PharmacyMedicineDTO getById(Long id) {
        return repository.findById(id)
                .map(PharmacyMedicineMapper::toDto)
                .orElseThrow(() -> new RuntimeException("PharmacyMedicine not found"));
    }

    @Override
    public List<PharmacyMedicineDTO> getByPharmacy(Long pharmacyId) {
        return repository.findByPharmacyId(pharmacyId)
                .stream()
                .map(PharmacyMedicineMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PharmacyMedicineDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(PharmacyMedicineMapper::toDto)
                .collect(Collectors.toList());
    }
}

