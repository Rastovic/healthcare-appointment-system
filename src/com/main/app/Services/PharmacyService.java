package com.main.app.Services;

import com.main.app.Dto.PharmacyDTO;
import com.main.app.Mappers.PharmacyMapper;
import com.main.app.Model.Pharmacy;
import com.main.app.Repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyDTO createPharmacy(PharmacyDTO dto) {
        Pharmacy entity = PharmacyMapper.toEntity(dto);
        Pharmacy saved = pharmacyRepository.save(entity);
        return PharmacyMapper.toDto(saved);
    }

    public List<PharmacyDTO> getAllPharmacies() {
        return pharmacyRepository.findAll()
                .stream()
                .map(PharmacyMapper::toDto)
                .collect(Collectors.toList());
    }

    public PharmacyDTO getPharmacy(Long id) {
        return pharmacyRepository.findById(id)
                .map(PharmacyMapper::toDto)
                .orElse(null);
    }
}
